package at.htl.quarkus_mqtt.mqtt;

import io.quarkus.runtime.StartupEvent;
import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class SampleDataProducer extends Thread{

    @Inject CamelPahoPublisher publisher;

    List<Room> rooms = new LinkedList<>();

    public void sendToPublisher(JSONObject jsonValue) throws MqttException {

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

        Map values = new HashMap<String, Object>();
        values.put("temp", System.currentTimeMillis());
        values.put("noise", Math.random() * 100);
        values.put("trafficlight", Math.floor(Math.random()));
        values.put("temperature", Math.random() * 20);
        values.put("humidity", Math.random() * 20);
        values.put("pressure", Math.random() * 1000);
        values.put("luminosity", Math.random() * 2000);
        values.put("co2", Math.random() * 700);
        values.put("motion", Math.floor(Math.random()));

        JSONObject jsonValue = new JSONObject(values);
        sendToPublisher(jsonValue);
    }
}
