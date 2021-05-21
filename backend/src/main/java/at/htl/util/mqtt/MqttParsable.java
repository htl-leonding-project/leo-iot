package at.htl.util.mqtt;

public interface MqttParsable {

    void fromMqtt(String topic, String data);

}
