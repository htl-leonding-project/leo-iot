package at.htl.control;

import at.htl.config.MqttConfiguration;
import at.htl.util.MqttSubscribe;
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

    public void subscribe(String topicFilter, MqttSubscribe mqttSubscribe) throws MqttException {
        getClient().subscribeWithResponse(topicFilter, mqttSubscribe::subscribe);
    }

}
