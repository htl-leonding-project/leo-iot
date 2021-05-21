package at.htl.util.mqtt;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface MqttSubscribe {
    void subscribe(String topic, MqttMessage message);
}
