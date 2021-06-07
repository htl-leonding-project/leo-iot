package at.htl.boundary.SensorResourceTest;

import com.intuit.karate.junit5.Karate;

public class SensorResourceTest {
    @Karate.Test
    Karate testCreateUnit() { return Karate.run("sensor-create.feature").relativeTo(getClass()); }

    @Karate.Test
    Karate testGetUnit() {
        return Karate.run("sensor-get.feature").relativeTo(getClass());
    }

    @Karate.Test
    Karate testDeleteUnit() {
        return Karate.run("sensor-delete.feature").relativeTo(getClass());
    }
}
