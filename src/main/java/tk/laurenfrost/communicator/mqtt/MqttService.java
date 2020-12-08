package tk.laurenfrost.communicator.mqtt;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.laurenfrost.communicator.service.BoardService;
import tk.laurenfrost.communicator.service.FoodService;
import tk.laurenfrost.communicator.service.SpeechToFoodService;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public final class MqttService {

    Logger logger = LoggerFactory.getLogger(MqttService.class);

    @Value("${mqtt.username}")
    private String username;
    @Value("${mqtt.password}")
    private String password;
    @Value("${mqtt.brokerUri}")
    private String brokerUri;

    private final BoardService boardService;
    private final SpeechToFoodService speechToFoodService;
    private final FoodService foodService;

    private final Map<String, MqttSubscriber> subscribers = new HashMap<>();
    private MqttClient client;

    @PostConstruct
    public void init() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setAutomaticReconnect(true);
        options.setCleanSession(false);
        options.setServerURIs(new String[]{brokerUri});
        try {
            client = new MqttClient(brokerUri, MqttClient.generateClientId(), new MemoryPersistence());
            client.connect(options);
            this.boardService.loadAllBoards().forEach(board -> listenBoard(board.getMacAddress()));
        } catch (MqttException error) {
            logger.error(error.getMessage(), error);
        }
    }

    public void listenBoard(String boardName) {
        if (!subscribers.containsKey(boardName)) {
            MqttSubscriber subscriber = new MqttSubscriber(speechToFoodService, foodService, boardService);
            try {
                client.subscribe(boardName, subscriber);
                subscribers.put(boardName, subscriber);
                logger.info("Subscribed to " + boardName);
            } catch (MqttException error) {
                logger.error(error.getMessage(), error);
            }
        }
    }

    public void stopListeningBoard(String boardName) {
        MqttSubscriber subscriber = subscribers.get(boardName);
        if (subscriber != null) {
            try {
                client.unsubscribe(boardName);
                subscribers.remove(boardName);
            } catch (MqttException error) {
                logger.error(error.getMessage(), error);
            }
        }
    }
}
