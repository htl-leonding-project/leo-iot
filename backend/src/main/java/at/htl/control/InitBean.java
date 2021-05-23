package at.htl.control;

import at.htl.entity.Measurement;
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
    void onStart(@Observes StartupEvent ev) { }
}
