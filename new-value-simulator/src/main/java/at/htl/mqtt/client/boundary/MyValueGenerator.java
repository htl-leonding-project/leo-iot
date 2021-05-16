package at.htl.mqtt.client.boundary;

import at.htl.mqtt.client.entity.Room;
import io.quarkus.runtime.StartupEvent;
import io.reactivex.Observable;
import io.smallrye.reactive.messaging.mqtt.MqttMessage;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
import at.htl.mqtt.client.repository.RoomRepository;

/**
 * https://stackoverflow.com/questions/62883516/publish-subscribe-mqtt-using-smallrye-reactive-messaging-dynamically
 * https://github.com/quarkusio/quarkus-quickstarts/blob/main/mqtt-quickstart/src/main/java/org/acme/mqtt/PriceGenerator.java
 */
@ApplicationScoped
public class MyValueGenerator {

    @Inject
    @Channel("topic-values")
    Emitter<byte[]> emitter;

    @Inject
    RoomRepository roomRepo;

     public void getAllRooms(){

        List<Room> rooms = roomRepo.getAllRooms();


        roomRepo.addRoom("edv10");
        roomRepo.addRoom("edv4");
        roomRepo.addRoom("107");


        for (Room room : rooms) {
            roomData(room);
        }
    }

    public void roomData(Room room){

        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribe(value -> {
                    Map values = new HashMap<String, Object>();
                    values.put("temp", System.currentTimeMillis());
                    values.put("noise", Math.random() * 100);
                    values.put("trafficlight", Math.floor(Math.random()));
                    values.put("temperature", temperatureValue());
                    values.put("humidity", Math.random() * 20);
                    values.put("pressure", Math.random() * 1000);
                    values.put("luminosity", Math.random() * 2000);
                    values.put("co2", Math.random() * 700);
                    values.put("motion", Math.floor(Math.random()));

                    JSONObject jsonValue = new JSONObject(values);

                    long timeStamp = jsonValue.getLong("temp");
                    emitter.send(MqttMessage.of("values/" + room.getName() + "/" + "noise" + "/" + "state", getBytes(jsonValue.getDouble("noise"), timeStamp)));
                    emitter.send(MqttMessage.of("values/" + room.getName() + "/" + "trafficlight" + "/" + "state", getBytes(jsonValue.getDouble("trafficlight"), timeStamp)));
                    emitter.send(MqttMessage.of("values/" + room.getName() + "/" + "temperature" + "/" + "state", getBytes(jsonValue.getDouble("temperature"), timeStamp)));
                    emitter.send(MqttMessage.of("values/" + room.getName() + "/" + "humidity" + "/" + "state", getBytes(jsonValue.getDouble("humidity"), timeStamp)));
                    emitter.send(MqttMessage.of("values/" + room.getName() + "/" + "pressure" + "/" + "state", getBytes(jsonValue.getDouble("pressure"), timeStamp)));
                    emitter.send(MqttMessage.of("values/" + room.getName() + "/" + "luminosity" + "/" + "state", getBytes(jsonValue.getDouble("luminosity"), timeStamp)));
                    emitter.send(MqttMessage.of("values/" + room.getName() + "/" + "co2" + "/" + "state", getBytes(jsonValue.getDouble("co2"), timeStamp)));
                    emitter.send(MqttMessage.of("values/" + room.getName() + "/" + "motion" + "/" + "state", getBytes(jsonValue.getDouble("motion"), timeStamp)));

                    System.out.println("Sending value -> " + jsonValue);
                });
    }

    void init(@Observes StartupEvent event) {
         getAllRooms();
    }

    Double oldValue = 20.00;
    public double temperatureValue()
    {
        Double returnValue;
        Double random = Math.random();

       if (oldValue < 18.00) {
           returnValue = oldValue + random/5;
           oldValue = returnValue;
       }
       else if (oldValue > 23.00) {
           returnValue = oldValue- random/5;
           oldValue = returnValue;
       }
       else {
           if (random > 0.6) {
               returnValue = oldValue + random/2;
               oldValue = returnValue;
           }
           else {
               returnValue = oldValue- random/2;
               oldValue = returnValue;
           }
       }
       return returnValue;
    }

    public byte[] getBytes(Object value, long timeStamp) {
        JSONObject json = new JSONObject();
        return json.put("value", value).put("timestamp", timeStamp).toString().getBytes();
    }
}
