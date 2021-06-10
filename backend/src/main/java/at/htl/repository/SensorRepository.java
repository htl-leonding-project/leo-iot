package at.htl.repository;

import at.htl.entity.Location;
import at.htl.entity.Sensor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.stream.Collectors;

@ApplicationScoped
public class SensorRepository extends Repository<Sensor, Long> {

    @Inject
    ThingRepository thingRepository;

    @Inject
    LocationRepository locationRepository;

    public Sensor getSensorFromMqttPath(String mqttPaht) {
        System.out.println(mqttPaht);
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

        System.out.println(locationStrings.length);
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

            System.out.println(location);
            lastLocation = location;
        }

        System.out.println(location);
        System.out.println("==========");

        return new Sensor();
    }
}
