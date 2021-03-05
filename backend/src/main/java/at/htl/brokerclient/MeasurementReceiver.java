package at.htl.brokerclient;

import at.htl.controller.MeasurementRepository;
import io.smallrye.reactive.messaging.mqtt.MqttMessage;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;
import java.util.concurrent.CompletionStage;
import javax.json.bind.JsonbBuilder;

@ApplicationScoped
public class MeasurementReceiver {

    @Inject
    MeasurementRepository measurementRepository;

    @Incoming("leo-iot")
    public CompletionStage<Void> receiver(MqttMessage<byte[]> msg) {
        JsonObject jsonObject = JsonbBuilder.create().fromJson(new String(msg.getPayload()), JsonObject.class);

        Measurement measurement = new Measurement(
                LocalDateTime.ofInstant(
                        Instant.ofEpochSecond(jsonObject.getInt("timestamp")),
                        TimeZone.getDefault().toZoneId()
                ),
                msg.getTopic().split("/")[2],
                jsonObject.getInt("value"),
                msg.getTopic().split("/")[1]
        );

        measurementRepository.save(measurement);

        return msg.ack();
    }
}
