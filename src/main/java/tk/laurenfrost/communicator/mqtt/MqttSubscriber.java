package tk.laurenfrost.communicator.mqtt;

import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.laurenfrost.communicator.entity.Board;
import tk.laurenfrost.communicator.entity.Food;
import tk.laurenfrost.communicator.service.BoardService;
import tk.laurenfrost.communicator.service.FoodService;
import tk.laurenfrost.communicator.service.SpeechToFoodService;

import java.util.List;

@RequiredArgsConstructor
class MqttSubscriber implements IMqttMessageListener {
    private final Logger logger = LoggerFactory.getLogger(MqttSubscriber.class);

    private final SpeechToFoodService speechToFoodService;
    private final FoodService foodService;
    private final BoardService boardService;


    @Override
    public void messageArrived(String topic, MqttMessage message) {
        logger.debug("Received a recording from " + topic);
        Board userBoard = boardService.getBoardByTopic(topic);
        List<String> foodNames = speechToFoodService.speechToFoodNames(message.getPayload());
        logger.info("The recording from " + topic + " contains consumer goods: " + foodNames);
        for (String foodName : foodNames) {
            Food food = new Food(foodName, userBoard);
            foodService.createFood(food);
        }
    }

}
