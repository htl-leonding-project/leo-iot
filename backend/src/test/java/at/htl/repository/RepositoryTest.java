package at.htl.repository;

import at.htl.entity.Location;
import at.htl.entity.Thing;
import at.htl.repository.LocationRepository;
import at.htl.repository.ThingRepository;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

@QuarkusTest
public class RepositoryTest {

    @Inject
    LocationRepository locationRepository;

    @Inject
    EntityManager entityManager;

    @Inject
    AgroalDataSource dataSource;

    private Location location1;
    private Location location2;
    private Location location3;

    private final String tableName = Location.class.getSimpleName();

    Table table;

    @BeforeEach
    @Transactional
    public void setup() {
        entityManager.createQuery(
                "delete from " + tableName
        ).executeUpdate();

        location1 = entityManager.merge(new Location(
                null,
                "HTL Leonding"
        ));

        location2 = entityManager.merge(new Location(
                location1,
                "ug"
        ));

        location3 = entityManager.merge(new Location(
                location2,
                "k03"
        ));
    }

    @Test
    public void test01_location_save() {
        Location location4 = new Location(
                location2,
                "k04"
        );

        locationRepository.save(location4);

        table = new Table(dataSource, tableName);
        output(table).toConsole();

        assertThat(table)
                .hasNumberOfRows(4)
                .row(3)
                .value("NAME")
                .isEqualTo("k04");
    }

//    @Test
//    public void test02_language_removeById() {
//        table = new Table(dataSource, tableName);
//
//        output(table).toConsole();
//        assertThat(table)
//                .hasNumberOfRows(3)
//                .row(0).value("LANGUAGENAME").isEqualTo("english")
//                .row(1).value("LANGUAGENAME").isEqualTo("german")
//                .row(2).value("LANGUAGENAME").isEqualTo("italiano");
//
//        System.out.println(languageRepository.removeById(0));
//
//        table = new Table(dataSource, tableName);
//        output(table).toConsole();
//        assertThat(table)
//                .hasNumberOfRows(2)
//                .row(0).value("LANGUAGENAME").isEqualTo("german")
//                .row(1).value("LANGUAGENAME").isEqualTo("italiano");
//    }
//
//    @Test
//    public void test03_language_remove() {
//        table = new Table(dataSource, tableName);
//
//        output(table).toConsole();
//        assertThat(table)
//                .hasNumberOfRows(3)
//                .row(0).value("LANGUAGENAME").isEqualTo("english")
//                .row(1).value("LANGUAGENAME").isEqualTo("german")
//                .row(2).value("LANGUAGENAME").isEqualTo("italiano");
//
//        Language language = languageRepository.findById(0);
//        languageRepository.removeById(0);
//
//        table = new Table(dataSource, tableName);
//        output(table).toConsole();
//        assertThat(table)
//                .hasNumberOfRows(2)
//                .row(0).value("LANGUAGENAME").isEqualTo("german")
//                .row(1).value("LANGUAGENAME").isEqualTo("italiano");
//    }
}
