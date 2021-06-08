package at.htl.boundary.SensorResourceTest;

import com.intuit.karate.junit5.Karate;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class SensorResourceTest {
    @Karate.Test
    Karate testCreateSensor() { return Karate.run("sensor-create.feature").relativeTo(getClass()); }

    @Karate.Test
    Karate testGetSensor() {
        return Karate.run("sensor-get.feature").relativeTo(getClass());
    }

    @Karate.Test
    Karate testDeleteSensor() {
        return Karate.run("sensor-delete.feature").relativeTo(getClass());
    }
}
