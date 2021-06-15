package at.htl.control;

import at.htl.repository.MeasurementRepository;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.paho.client.mqttv3.MqttException;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

public class MeasurementController {

    @Inject
    MqttController mqttController;

    @Inject
    MeasurementRepository repository;

    public void init(@Observes StartupEvent event) throws MqttException {
        mqttController.subscribe("#", repository, (topic, measurement) -> repository.save(measurement));
    }

}
