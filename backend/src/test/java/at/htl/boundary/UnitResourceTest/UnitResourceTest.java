package at.htl.boundary.UnitResourceTest;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class UnitResourceTest {
    @Karate.Test
    Karate testCreateTemplateApplicationAsStudent() {
        return Karate.run("template-application-creation").relativeTo(getClass());
    }
}
