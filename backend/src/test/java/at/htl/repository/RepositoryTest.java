package at.htl.repository;

import at.htl.entity.Location;
import io.agroal.api.AgroalDataSource;
import io.quarkus.arc.ArcUndeclaredThrowableException;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
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
    public void test00_location_checkData() {
        table = new Table(dataSource, tableName);
        output(table).toConsole();

        assertThat(table)
                .hasNumberOfRows(3)
                .row(0).value("name").isEqualTo("HTL Leonding")
                .row(1).value("name").isEqualTo("ug")
                .row(2).value("name").isEqualTo("k03");
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

    @Test
    public void test02_location_removeById_success() {
        assertThat(locationRepository.removeById(location3.getId()))
                .isTrue();

        table = new Table(dataSource, tableName);
        output(table).toConsole();

        assertThat(table)
                .hasNumberOfRows(2)
                .row(0).value("name").isEqualTo("HTL Leonding")
                .row(1).value("name").isEqualTo("ug");
    }

    @Test
    public void test03_location_removeById_fail() {
        assertThat(locationRepository.removeById(5L))
                .isFalse();
    }

    @Test
    public void test04_location_removeById_cascade() {
        assertThatThrownBy(() -> locationRepository.removeById(location1.getId()))
                .isInstanceOf(ArcUndeclaredThrowableException.class);
    }
}
