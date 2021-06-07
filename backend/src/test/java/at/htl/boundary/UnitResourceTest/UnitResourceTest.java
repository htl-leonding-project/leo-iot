package at.htl.boundary.UnitResourceTest;

import com.intuit.karate.junit5.Karate;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class UnitResourceTest {
    @Karate.Test
    Karate testCreateUnit() {
        return Karate.run("unit-create.feature").relativeTo(getClass());
    }

    @Karate.Test
    Karate testGetUnit() {
        return Karate.run("unit-get.feature").relativeTo(getClass());
    }
}
