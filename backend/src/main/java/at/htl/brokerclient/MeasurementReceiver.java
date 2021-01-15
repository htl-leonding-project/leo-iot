package at.htl.brokerclient;

import io.smallrye.reactive.messaging.mqtt.MqttMessage;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import java.util.concurrent.CompletionStage;

public class MeasurementReceiver {

    @Incoming("leo-iot")
    public CompletionStage<Void> receiver(MqttMessage<byte[]> msg) {
        System.out.println(msg.getTopic() + ": " + new String(msg.getPayload()));
        return msg.ack();
    }
}
