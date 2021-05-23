package at.htl.control;

import at.htl.config.MqttConfiguration;
import at.htl.util.mqtt.MqttParseSubscribe;
import at.htl.util.mqtt.MqttParsable;
import at.htl.util.mqtt.MqttSubscribe;
import io.quarkus.runtime.ShutdownEvent;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.concurrent.*;


/**
 * This Class is for the communication with the mqtt broker
 * @author QuirinEcker
 */
@ApplicationScoped
public class MqttController {

    @Inject
    MqttConfiguration configuration;

    private MqttClient mqttClient;

    /**
     * This methods initializes the client in the controller
     * @throws MqttException if credentials are wrong or the broker is offline this Exception will be thrown
     */
    private void init() throws MqttException {
        mqttClient =  new MqttClient(
                configuration.getFullUrl(),
                MqttClient.generateClientId(),
                new MemoryPersistence()
        );

        MqttConnectOptions options = new MqttConnectOptions();

        if (configuration.password.isPresent() && configuration.username.isPresent()) {
            options.setUserName(configuration.username.get());
            options.setPassword(configuration.password.get().toCharArray());
        }

        options.setCleanSession(true);

        mqttClient.connect(options);
    }

    /**
     * This method will return the camel paho Mqtt Client. If the client is null it will create it with the init()
     * function.
     * @return Camel Paho Mqtt Client
     */
    public MqttClient getClient() {
        if (mqttClient == null) {
            try {
                init();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

        return mqttClient;
    }


    /**
     * Disconnects the Broker at the end of the quarkus lifecycle
     * @param ev quarkus shutdown event (will be ignored)
     * @throws MqttException will throw if disconnect of the broker fails
     */
    void cleanUp(@Observes ShutdownEvent ev) throws MqttException {
        getClient().disconnect();
    }

    /**
     * This method is for subscribing to a mqtt topic and parsing the output to a java class. Keep in mind The java
     * class needs to implement MqttParsable
     * @param topicFilter mqtt topic to subscribe
     * @param mqttHandler callback for mqtt values
     * @param Type parse type
     * @param <T> parse type
     * @throws MqttException will be thrown if there is an error with mqtt
     */
    public <T extends MqttParsable> void subscribe(String topicFilter, MqttParseSubscribe<T> mqttHandler, Class<T> Type) throws MqttException {
        subscribe(topicFilter, (topic, message) -> {
            try {
                T object = Type.getDeclaredConstructor().newInstance();
                object.fromMqtt(topic, new String(message.getPayload()));
                mqttHandler.subscribe(topic, object);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    /**
     * This method is for subscribing to a mqtt topic without parsing. This will just Provide a value of MqttMessage and
     * the given topic
     * @param topicFilter mqtt topic to subscribe
     * @param mqttSubscribe callback for mqtt values
     * @throws MqttException will be thrown if there is an error with mqtt
     */
    public void subscribe(String topicFilter, MqttSubscribe mqttSubscribe) throws MqttException {
        getClient().subscribeWithResponse(topicFilter, mqttSubscribe::subscribe);
    }
}
