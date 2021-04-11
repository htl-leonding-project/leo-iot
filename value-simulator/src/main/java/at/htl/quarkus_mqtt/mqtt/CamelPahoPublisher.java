package at.htl.quarkus_mqtt.mqtt;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Path("publish")
public class CamelPahoPublisher {

    @Inject
    PahoConfiguration config;
    @Inject
    SampleDataProducer producer;

    MqttClient client;

    void onStart(@Observes StartupEvent ev) throws MqttException {
        // new MqttClient(url out of the config file, client id [is random generated], new MemoryPersistence)
        client = new MqttClient(
                config.url,
                MqttClient.generateClientId(),
                new MemoryPersistence()
        );

        // Options for the connection to the mqtt broker
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(config.username);
        options.setPassword(config.password.toCharArray());

        // connecting to the mqtt broker
        client.connect(options);
        //client.subscribe(config.topicWrite, config.qos);

        producer.run();
    }

    // will publish the passed through String to the configured topic-write
    public void publish(JSONObject message, String topicToWrite) throws MqttException {
        long timeStamp = message.getLong("temp");
        client.publish(config.topicWrite + topicToWrite + "/" + "noise" + "/" + "state", new MqttMessage(getBytes(message.getDouble("noise"), timeStamp)));
        client.publish(config.topicWrite + topicToWrite + "/" + "trafficlight" + "/" + "state", new MqttMessage(getBytes(message.getDouble("trafficlight"), timeStamp)));
        client.publish(config.topicWrite + topicToWrite + "/" + "temperature" + "/" + "state", new MqttMessage(getBytes(message.getDouble("temperature"), timeStamp)));
        client.publish(config.topicWrite + topicToWrite + "/" + "humidity" + "/" + "state", new MqttMessage(getBytes(message.getDouble("humidity"), timeStamp)));
        client.publish(config.topicWrite + topicToWrite + "/" + "pressure" + "/" + "state", new MqttMessage(getBytes(message.getDouble("pressure"), timeStamp)));
        client.publish(config.topicWrite + topicToWrite + "/" + "luminosity" + "/" + "state", new MqttMessage(getBytes(message.getDouble("luminosity"), timeStamp)));
        client.publish(config.topicWrite + topicToWrite + "/" + "co2" + "/" + "state", new MqttMessage(getBytes(message.getDouble("co2"), timeStamp)));
        client.publish(config.topicWrite + topicToWrite + "/" + "motion" + "/" + "state", new MqttMessage(getBytes(message.getDouble("motion"), timeStamp)));
    }

    public byte[] getBytes(Object value, long timeStamp) {
        JSONObject json = new JSONObject();
        return json.put("value", value).put("timestamp", timeStamp).toString().getBytes();
    }

    // (POST localhost:8080/publish) will publish the body content to the mqtt broker.
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void post(MessageTopicDto messageAndTopic) throws MqttException {
        this.publish(messageAndTopic.message, messageAndTopic.topicToWrite);
    }
}
