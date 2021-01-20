package at.htl.brokerclient;

import at.htl.controller.MeasurementRepository;
import at.htl.entity.Measurement;
import io.smallrye.reactive.messaging.mqtt.MqttMessage;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZoneId;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ForkJoinWorkerThread;
import javax.json.bind.JsonbBuilder;

@ApplicationScoped
public class MeasurementReceiver {

    @Inject
    MeasurementRepository measurementRepository;

    @Inject
    EntityManager em;

    @Incoming("leo-iot")
    public CompletionStage<Void> receiver(MqttMessage<byte[]> msg) {
        JsonObject jsonObject = JsonbBuilder.create().fromJson(new String(msg.getPayload()), JsonObject.class);
        System.out.println(jsonObject.getInt("value"));

        Instant instant = Instant.ofEpochMilli(jsonObject.getInt("timestamp"));


        Measurement measurement = new Measurement(
                instant.atZone(ZoneId.systemDefault()).toLocalDateTime(),
                msg.getTopic().split("/")[2],
                jsonObject.getInt("value"),
                msg.getTopic().split("/")[1]
        );

        // em.merge(measurement);

        return msg.ack();
    }
}
