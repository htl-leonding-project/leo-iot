package at.htl.util.mqtt;

/**
 * Interface for every class which is parsable with data coming from the mqtt broker
 * @author QuirinEcker
 */
public interface MqttParsable {

    /**
     * This method is used for parsing mqtt data into a java object
     * @param topic The evaluated mqtt topic
     * @param data The data coming from the topic
     */
    void fromMqtt(String topic, String data);

}
