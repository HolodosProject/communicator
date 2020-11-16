package tk.laurenfrost.communicator.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import tk.laurenfrost.communicator.alexeyapi.AlexeyApiClass;

public class MqttSubscriber implements IMqttMessageListener {

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        AlexeyApiClass.processSpeech(topic, message.getPayload());
    }

}
