package at.htl.boundary.MeasurementResourceTest;

import com.intuit.karate.junit5.Karate;

public class MeasuremetResourceTest {

    @Karate.Test
    Karate testCreateMeasurement() { return Karate.run("measurement-create.feature").relativeTo(getClass()); }

}
