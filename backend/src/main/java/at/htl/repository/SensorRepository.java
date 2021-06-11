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
        String sensorString = pathSegments[pathSegments.length - 2];
        String thingString = pathSegments[pathSegments.length - 3];
        String[] locationStrings = Arrays
                .stream(pathSegments)
                .limit(pathSegments.length - 3)
                .collect(Collectors.toList())
                .toArray(new String[pathSegments.length - 3]);

        var location = locationRepository
                .getLocationByTree(locationStrings);

        var thing = thingRepository
                .getOrCreateByTree(thingString, location);

        var sensorType = sensorTypeRepository
                .getOrCreateByName(sensorString);

        return getOrCreateSensorByTree(sensorType, thing);
    }

    private Sensor getOrCreateSensorByTree(SensorType sensorType, Thing thing) {
        var query = getEntityManager().createQuery(
                "select s from Sensor s where s.sensorType = :sensorType and s.thing = :thing",
                Sensor.class
        );

        query.setParameter("sensorType", sensorType);
        query.setParameter("thing", thing);

        return query
                .getResultStream()
                .findFirst()
                .orElse(save(new Sensor(
                    thing,
                    sensorType
                )));
    }
}
