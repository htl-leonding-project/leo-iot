package at.htl.util.mqtt;

import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * This interface is for the subscribe callback
 * @see at.htl.control.MqttController
 * @author QuirinEcker
 */
public interface MqttSubscribe {

    /**
     * callback
     * @param topic topic for subscribed for the given data
     * @param message data for the given topic
     */
    void subscribe(String topic, MqttMessage message);
}
