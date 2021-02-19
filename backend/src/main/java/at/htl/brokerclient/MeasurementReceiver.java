package at.htl.brokerclient;

import at.htl.controller.MeasurementRepository;
import at.htl.entity.Measurement;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.reactive.messaging.mqtt.MqttMessage;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.hibernate.reactive.mutiny.Mutiny;
import org.hibernate.tuple.entity.EntityMetamodel;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import java.time.Instant;
import java.time.ZoneId;
import java.util.concurrent.CompletionStage;
import javax.json.bind.JsonbBuilder;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class MeasurementReceiver {

    @Inject
    MeasurementRepository measurementRepository;

    @Incoming("leo-iot")
    public CompletionStage<Void> receiver(MqttMessage<byte[]> msg) {
        JsonObject jsonObject = JsonbBuilder.create().fromJson(new String(msg.getPayload()), JsonObject.class);
        Instant instant = Instant.ofEpochMilli(jsonObject.getInt("timestamp"));

        Measurement measurement = new Measurement(
                instant.atZone(ZoneId.systemDefault()).toLocalDateTime(),
                msg.getTopic().split("/")[2],
                jsonObject.getInt("value"),
                msg.getTopic().split("/")[1]
        );

        measurementRepository.save(measurement);

        return msg.ack();
    }
}
