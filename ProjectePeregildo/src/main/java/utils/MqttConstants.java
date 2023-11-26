package utils;

/**
 * The MqttConstants class contains constants related to MQTT (Message Queuing Telemetry Transport) configuration.
 */
public class MqttConstants {
    /**
     * The address of the MQTT broker, including the protocol and port number.
     */
    public static final String BROKER = "tcp://84.88.76.18:1883";

    /**
     * The MQTT topic to subscribe or publish messages to.
     */
    public static final String TOPIC = "hackeps/hackeps/eurecat";

    /**
     * The client identifier used when connecting to the MQTT broker.
     */
    public static final String CLIENT = "ZumitoTeam";
}
