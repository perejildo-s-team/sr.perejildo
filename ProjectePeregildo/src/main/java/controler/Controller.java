package controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.StatusDAO;
import model.Status;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import utils.DBConnection;
import utils.MqttConstants;

public class Controller {
    public void init() {
        try {
            MemoryPersistence persistence = new MemoryPersistence();
            MqttClient client = new MqttClient(MqttConstants.BROKER, MqttConstants.CLIENT, persistence);
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                    System.out.println("Connection has been lost");
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    ObjectMapper mapper = new ObjectMapper();
                    Status status = mapper.readValue(new String(mqttMessage.getPayload()), Status.class);
                    DBConnection dbConnection = new DBConnection();
                    StatusDAO dao = new StatusDAO(dbConnection.connect());
                    dao.insertStatus(status);
                    dbConnection.disconnect();
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("Delivery has been completed");
                    try {
                        client.disconnect();
                    } catch (MqttException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            client.connect(getConnOptions());
            client.subscribe(MqttConstants.TOPIC);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    private MqttConnectOptions getConnOptions() {
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName(System.getenv("MQTT_USERNAME"));
        connOpts.setPassword(System.getenv("MQTT_PASSWORD").toCharArray());
        connOpts.setKeepAliveInterval(60);
        return connOpts;
    }
}
