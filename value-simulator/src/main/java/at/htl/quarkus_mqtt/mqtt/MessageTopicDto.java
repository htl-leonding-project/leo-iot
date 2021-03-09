package at.htl.quarkus_mqtt.mqtt;

import org.json.JSONObject;

import javax.json.JsonObject;

public class MessageTopicDto {
    public JSONObject message;
    public String topicToWrite;

    public MessageTopicDto() {
    }

    public MessageTopicDto(JSONObject message, String topicToWrite) {
        this.message = message;
        this.topicToWrite = topicToWrite;
    }
}
