package at.htl.util.mqtt;

public interface MqttParseSubscribe<T extends MqttParsable> {
    void subscribe(String topic, T object);
}
