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

@ApplicationScoped
public class MqttController {

    @Inject
    MqttConfiguration configuration;

    private MqttClient mqttClient;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    void init() throws MqttException {
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

    private MqttClient getClient() {
        if (mqttClient == null) {
            try {
                init();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

        return mqttClient;
    }

    void cleanUp(@Observes ShutdownEvent ev) throws MqttException {
        getClient().disconnect();
    }

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

    public void subscribe(String topicFilter, MqttSubscribe mqttSubscribe) throws MqttException {
        getClient().subscribeWithResponse(topicFilter, mqttSubscribe::subscribe);
    }
}
