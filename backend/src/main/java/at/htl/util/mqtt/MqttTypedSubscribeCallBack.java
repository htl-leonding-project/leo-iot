package at.htl.util.mqtt;

/**
 * This interface is for the subscribe with parse callback
 * @see at.htl.control.MqttController
 * @param <T> target parse type
 * @author QuirinEcker
 */
public interface MqttTypedSubscribeCallBack<T> {

    /**
     * callback
     * @param topic topic for subscribed for the given data
     * @param object parsed data for the given topic
     */
    void callback(String topic, T object);
}
