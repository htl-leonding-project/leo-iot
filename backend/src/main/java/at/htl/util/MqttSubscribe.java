package at.htl.util;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface MqttSubscribe {

    void subscribe(String topic, MqttMessage message);

}
