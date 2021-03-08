package at.htl.quarkus_mqtt.mqtt;

import io.quarkus.runtime.StartupEvent;
import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.paho.client.mqttv3.MqttException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.Json;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class SampleDataProducer extends Thread{

    @Inject CamelPahoPublisher publisher;

    List<Room> rooms = new LinkedList<>();

    public void sendToPublisher(String jsonValue) throws MqttException {

        rooms.add(new Room("4ahif"));
        rooms.add(new Room("3ahif"));
        rooms.add(new Room("5ahitm"));
        rooms.add(new Room("4ahitm"));
        rooms.add(new Room("EDV10"));

        for (Room room : rooms) {
            publisher.post(new MessageTopicDto(jsonValue, room.getName()));
        }

    }

    public void run() {
        while(true){
            try {
                produce();
            } catch (MqttException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void produce() throws MqttException {
        var timestamp = new Timestamp(System.currentTimeMillis());
        var json = Json.createObjectBuilder()
                .add("timestamp", timestamp.toString())
                .add("co2Indoor", Math.random())
                .add("humidityIndoor", Math.random())
                .add("temperatureOutdoor", Math.random())
                .add("window1open", Math.random() % 0.2 == 0)
                .add("window2open", Math.random() % 0.2 == 0)
                .add("window3open", Math.random() % 0.2 == 0)
                .add("window3open", Math.random() % 0.2 == 0)
                .build();

        sendToPublisher(json.toString());
    }
}
