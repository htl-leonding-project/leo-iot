package at.htl.quarkus_mqtt.mqtt;

public class MessageTopicDto {
    public String message;
    public String topicToWrite;

    public MessageTopicDto() {
    }

    public MessageTopicDto(String message, String topicToWrite) {
        this.message = message;
        this.topicToWrite = topicToWrite;
    }
}
