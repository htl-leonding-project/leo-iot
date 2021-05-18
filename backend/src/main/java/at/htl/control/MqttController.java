package at.htl.control;

import at.htl.config.MqttConfiguration;
import at.htl.util.MqttSubscribe;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@ApplicationScoped
public class MqttController {

    @Inject
    MqttConfiguration configuration;

    private Future<MqttClient> mqttClient;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    void init(@Observes StartupEvent ev) {
        mqttClient = executor.submit(() -> {
            try {
                var client =  new MqttClient(
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

                client.connect(options);

                return client;
            } catch (MqttException e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    void cleanUp(@Observes ShutdownEvent ev) throws MqttException, ExecutionException, InterruptedException {
        mqttClient.get().disconnect();
    }

    public void subscribe(String topic, MqttSubscribe mqttSubscribe) throws ExecutionException, InterruptedException, MqttException {
        mqttClient.get().subscribeWithResponse(topic, ((s, mqttMessage) -> mqttSubscribe.subscribe(topic, mqttMessage)));
    }

}
