package at.htl.util.mqtt;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface MqttParseCallback<T> {

    T parseFromMqtt(String topic, MqttMessage message);
}
