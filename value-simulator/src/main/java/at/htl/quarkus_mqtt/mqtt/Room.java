package at.htl.quarkus_mqtt.mqtt;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.json.Json;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class Room {
    String name;

    public Room(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
