package at.htl.mqtt.client.boundary;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//@ApplicationScoped
//public class ValueGenerator {
//
//    private final Random random = new Random();
//
//    @Outgoing("topic-price")
//    public Flowable<Integer> generate() {
//        return Flowable.interval(5, TimeUnit.SECONDS)
//                .map(tick -> {
//                    int price = random.nextInt(100);
//                    System.out.println("Sending price: " + price);
//                    return price;
//                });
//    }
//
//}

// https://stackoverflow.com/questions/62883516/publish-subscribe-mqtt-using-smallrye-reactive-messaging-dynamically