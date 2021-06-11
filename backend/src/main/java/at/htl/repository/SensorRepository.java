package at.htl.repository;

import at.htl.entity.*;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.stream.Collectors;

@ApplicationScoped
public class SensorRepository extends Repository<Sensor, Long> {

    @Inject
    ThingRepository thingRepository;

    @Inject
    LocationRepository locationRepository;

    @Inject
    SensorTypeRepository sensorTypeRepository;

    @Transactional
    public Sensor getSensorFromMqttPath(String mqttPaht) {
        var pathSegments = mqttPaht.split("/");

        String stateString = pathSegments[pathSegments.length - 1];
        String sensorString = pathSegments[pathSegments.length - 2];
        String thingString = pathSegments[pathSegments.length - 3];
        String[] locationStrings = Arrays
                .stream(pathSegments)
                .limit(pathSegments.length - 3)
                .collect(Collectors.toList())
                .toArray(new String[pathSegments.length - 3]);

        Location lastLocation = null;
        Location location = null;

        for (int i = 0; i < locationStrings.length; i++) {
            if (i == 0) {
                location = locationRepository.getLocationByParentLocationAndName(
                        null,
                        locationStrings[i]
                );

                if (location == null) {
                    location = locationRepository.save(new Location(
                            null,
                            locationStrings[i]
                    ));
                }

            } else {
                location = locationRepository.getLocationByParentLocationAndName(
                        lastLocation,
                        locationStrings[i]
                );

                if (location == null) {
                    location = locationRepository.save(new Location(
                            lastLocation,
                            locationStrings[i]
                    ));
                }
            }

            lastLocation = location;
        }

        var thing = thingRepository.getThingByNameAndLocation(
                thingString, location
        );

        if (thing == null) {
            thing = thingRepository.save(new Thing(
                    location, thingString
            ));
        }

        var sensorType = sensorTypeRepository.find("name", sensorString)
                .firstResultOptional()
                .orElse(null);

        if (sensorType == null) {
            sensorType = sensorTypeRepository.save(new SensorType(
                    sensorString,
                    null
            ));
        }


        Sensor sensor = getSensorBySensorTypeAndThing(sensorType, thing);

        if (sensor == null) {
            sensor = save(new Sensor(
                    thing,
                    sensorType
            ));
        }

        return sensor;
    }

    private Sensor getSensorBySensorTypeAndThing(SensorType sensorType, Thing thing) {
        var query = getEntityManager().createQuery(
                "select s from Sensor s where s.sensorType = :sensorType and s.thing = :thing",
                Sensor.class
        );

        query.setParameter("sensorType", sensorType);
        query.setParameter("thing", thing);

        return query
                .getResultStream()
                .findFirst()
                .orElse(null);
    }
}
