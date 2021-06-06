package at.htl.boundary.UnitResourceTest;

import com.intuit.karate.junit5.Karate;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class UnitResourceTest {
    @Karate.Test
    Karate testCreateUnit() {
        return Karate.run("unit-create").relativeTo(getClass());
    }
}
