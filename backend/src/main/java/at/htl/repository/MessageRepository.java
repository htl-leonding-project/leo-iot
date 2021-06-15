package at.htl.repository;

import at.htl.entity.*;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.stream.Collectors;

@ApplicationScoped
public class MessageRepository {

    @Inject
    MeasurementRepository measurementRepository;

    @Inject
    LocationRepository locationRepository;

    @Inject
    ThingRepository thingRepository;

    @Inject
    SensorTypeRepository sensorTypeRepository;

    @Inject
    ActorTypeRepository actorTypeRepository;

    @Inject
    SensorRepository sensorRepository;

    @Inject
    ActorRepository actorRepository;

    @Inject
    ActorActionRepository actorActionRepository;

    @Transactional
    public void processingMessage(String topic, MqttMessage message) {
        JsonObject object = JsonbBuilder
                .create()
                .fromJson(new String(message.getPayload()), JsonObject.class);

        var pathSegments = topic.split("/");
        String deviceString = pathSegments[pathSegments.length - 2];
        String thingString = pathSegments[pathSegments.length - 3];
        String[] locationStrings = Arrays
                .stream(pathSegments)
                .limit(pathSegments.length - 3)
                .collect(Collectors.toList())
                .toArray(new String[pathSegments.length - 3]);
        double value = object.getJsonNumber("value").doubleValue();
        // * 1000 for converting seconds to milliseconds

        var timeStamp = new Timestamp(object.getJsonNumber("timestamp").longValue() * 1000);
        var location = locationRepository.getLocationByTree(locationStrings);
        var thing = thingRepository.getOrCreateByTree(thingString, location);

        var sensorType = sensorTypeRepository.getByName(deviceString);
        var actorType = actorTypeRepository.getByName(deviceString);

        if (sensorType.isPresent()) {
            var sensor = sensorRepository.getOrCreateSensorByTree(sensorType.get(), thing);
            measurementRepository.save(processMeasurement(
                    sensor,
                    timeStamp,
                    value
            ));
        } else if (actorType.isPresent()) {
            var actor = actorRepository.getOrCreateActorByTree(actorType.get(), thing, value);
            actor.setValue(value);
            actorActionRepository.save(processActorAction(
                    actor,
                    timeStamp,
                    value
            ));
        } else {
            var newSensorType = sensorTypeRepository.save(
                    new SensorType(deviceString, null)
            );

            var sensor = sensorRepository.save(
                    new Sensor(thing, newSensorType)
            );

            try {
                measurementRepository.persist(processMeasurement(
                        sensor,
                        timeStamp,
                        value
                ));
            } catch (Exception e) {
                System.out.println("This is not working");
            }
        }
    }

    private Measurement processMeasurement(Sensor sensor, Timestamp timeStamp, double value) {
        System.out.println(new Measurement(
                new Measurement.MeasurementKey(
                        timeStamp,
                        sensor
                ),
                value
        ));

        return new Measurement(
                new Measurement.MeasurementKey(
                        timeStamp,
                        sensor
                ),
                value
        );
    }

    private ActorAction processActorAction(Actor actor, Timestamp timeStamp, double value) {
        return new ActorAction(
                new ActorAction.ActorActionKey(
                        timeStamp,
                        actor
                ),
                value
        );
    }
}
