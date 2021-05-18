package at.htl.control;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.paho.client.mqttv3.MqttException;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.concurrent.ExecutionException;

public class InitBean {

    @Inject
    EntityManager em;

    @Inject
    MqttController controller;

    @Transactional
    void onStart(@Observes StartupEvent ev) throws MqttException, ExecutionException, InterruptedException {
        controller.subscribe("og/+/+/state", (topic, message) -> System.out.println(topic + ": " + new String(message.getPayload())));
    }
}
