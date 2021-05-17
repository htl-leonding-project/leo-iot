package at.htl.control;

import at.htl.config.MqttConfiguration;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class MqttController {

    @Inject
    MqttConfiguration configuration;

    private MqttClient mqttClient;

    void init(@Observes StartupEvent ev) throws MqttException {
        mqttClient = new MqttClient(
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

    void cleanUp(@Observes ShutdownEvent ev) throws MqttException {
        mqttClient.disconnect();
    }

}
