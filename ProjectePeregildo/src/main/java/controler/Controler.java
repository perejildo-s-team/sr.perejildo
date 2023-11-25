package controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Status;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import utils.MqttConstants;

public class Controler {
    public void init() {
        try {
            MemoryPersistence persistence = new MemoryPersistence();
            MqttClient client = new MqttClient(MqttConstants.BROKER, MqttConstants.CLIENT, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName(System.getenv("MQTT_USERNAME"));
            connOpts.setPassword(System.getenv("MQTT_PASSWORD").toCharArray());
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {

                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    ObjectMapper mapper = new ObjectMapper();
                    Status status = mapper.readValue(new String(mqttMessage.getPayload()), Status.class);
                    System.out.println(s + ": " + status);

                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                }
            });
            client.connect(connOpts);
            client.subscribe(MqttConstants.TOPIC);

            //client.disconnect();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

    }
}
