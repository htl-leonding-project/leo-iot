package at.htl.quarkus_mqtt.mqtt;

import io.quarkus.runtime.StartupEvent;
import io.reactivex.Flowable;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("publish")
public class CamelPahoPublisher {

    @Inject PahoConfiguration config;
    @Inject SampleDataProducer producer;

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
        options.setPassword(config.password.toCharArray());
        options.setUserName(config.username);
        options.setCleanSession(true);

        // connecting to the mqtt broker
        client.connect(options);

        producer.run();
    }

    // will publish the passed through String to the configured topic-write
    public void publish(String message, String topicToWrite) throws MqttException {
        client.publish(config.topicWrite + topicToWrite, new MqttMessage(message.getBytes()));
    }

    // (POST localhost:8080/publish) will publish the body content to the mqtt broker.
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void post(MessageTopicDto messageAndTopic) throws MqttException {
        this.publish(messageAndTopic.message, messageAndTopic.topicToWrite);
    }
}
