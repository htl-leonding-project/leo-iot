package at.htl.mqtt.client.boundary;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//@ApplicationScoped
//public class ValueGenerator2 {
//
//    private final Random random = new Random();
//
//    @Outgoing("topic-values")
//    public Flowable<Integer> generate() {
//        return Flowable.interval(6, TimeUnit.SECONDS)
//                .map(tick -> {
//                    int price = random.nextInt(100);
//                    System.out.println("Sending values: " + price);
//                    return price;
//                });
//    }
//
//}
