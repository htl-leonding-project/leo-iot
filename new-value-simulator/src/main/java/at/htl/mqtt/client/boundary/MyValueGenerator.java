package at.htl.mqtt.client.boundary;

import io.quarkus.runtime.StartupEvent;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.smallrye.reactive.messaging.mqtt.MqttMessage;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * https://stackoverflow.com/questions/62883516/publish-subscribe-mqtt-using-smallrye-reactive-messaging-dynamically
 * https://github.com/quarkusio/quarkus-quickstarts/blob/main/mqtt-quickstart/src/main/java/org/acme/mqtt/PriceGenerator.java
 */
@ApplicationScoped
public class MyValueGenerator {

    @Inject
    @Channel("topic-values")
    Emitter<Double> emitter;

    Random rnd = new Random();

    void init(@Observes StartupEvent event) {

        String[] topics = new String[]{"values/raum1", "values/raum2", "values/raum3"};
        for (String topic : topics) {

            Observable.interval(0, 5, TimeUnit.SECONDS)
                    .subscribe(value -> {
                        double payload = rnd.nextDouble();
                        emitter.send(MqttMessage.of(topic, payload));
                        System.out.println("Sending value -> " + payload);
                    });
        }
    }


}
