package controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.StatusDAO;
import model.Status;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import utils.DBConnection;
import utils.MqttConstants;

/**
 * The Controller class manages the initialization and configuration of an MQTT client.
 */
public class Controller {

    /**
     * Initializes the MQTT client, sets up callback functions, connects to the broker, and subscribes to a topic.
     */
    public void init() {
        try {
            // Set up MQTT client with persistence
            MemoryPersistence persistence = new MemoryPersistence();
            MqttClient client = new MqttClient(MqttConstants.BROKER, MqttConstants.CLIENT, persistence);

            // Set up callback functions for connection, message arrival, and delivery completion
            client.setCallback(createMqttCallback(client));

            // Connect to the MQTT broker and subscribe to the specified topic
            client.connect(getConnOptions());
            client.subscribe(MqttConstants.TOPIC);
        } catch (MqttException e) {
            // Handle MQTT-related exceptions
            handleMqttException(e);
        }
    }

    /**
     * Creates a MqttCallback implementation with defined behavior for connection loss, message arrival, and delivery completion.
     *
     * @param client The MQTT client to associate with the callback.
     * @return The created MqttCallback instance.
     */
    private MqttCallback createMqttCallback(MqttClient client) {
        return new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {
                System.out.println("Connection has been lost");
            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) {
                // Handle incoming MQTT messages
                handleIncomingMessage(client, mqttMessage);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                // Handle MQTT message delivery completion
                handleDeliveryComplete(client);
            }
        };
    }

    /**
     * Handles incoming MQTT messages by deserializing the payload and storing the status in the database.
     *
     * @param client      The MQTT client receiving the message.
     * @param mqttMessage The received MQTT message.
     */
    private void handleIncomingMessage(MqttClient client, MqttMessage mqttMessage) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Status status = mapper.readValue(new String(mqttMessage.getPayload()), Status.class);

            // Connect to the database, insert the status, and disconnect
            DBConnection dbConnection = new DBConnection();
            StatusDAO dao = new StatusDAO(dbConnection.connect());
            dao.insertStatus(status);
            dbConnection.disconnect();
        } catch (Exception e) {
            // Handle exceptions during message processing
            handleException(e);
        }
    }

    /**
     * Handles MQTT message delivery completion by disconnecting the client.
     *
     * @param client The MQTT client that completed message delivery.
     */
    private void handleDeliveryComplete(MqttClient client) {
        System.out.println("Delivery has been completed");
        try {
            client.disconnect();
        } catch (MqttException e) {
            // Handle MQTT-related exceptions during disconnection
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles general exceptions by wrapping them in a RuntimeException.
     *
     * @param e The exception to handle.
     */
    private void handleException(Exception e) {
        throw new RuntimeException(e);
    }

    /**
     * Configures and returns MQTT connection options.
     *
     * @return The configured MQTT connection options.
     */
    private MqttConnectOptions getConnOptions() {
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName(System.getenv("MQTT_USERNAME"));
        connOpts.setPassword(System.getenv("MQTT_PASSWORD").toCharArray());
        connOpts.setKeepAliveInterval(60);
        return connOpts;
    }

    /**
     * Handles MQTT-related exceptions by wrapping them in a RuntimeException.
     *
     * @param e The MQTT-related exception to handle.
     */
    private void handleMqttException(MqttException e) {
        throw new RuntimeException(e);
    }
}
