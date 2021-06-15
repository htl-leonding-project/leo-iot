package at.htl.control;

import at.htl.entity.*;
import at.htl.repository.*;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MqttMessageController {

    @Inject
    MqttController mqttController;

    @Inject
    MessageRepository repository;

    public void init(@Observes StartupEvent event) throws MqttException {
        mqttController.subscribe("#", repository::processingMessage);
    }
}
