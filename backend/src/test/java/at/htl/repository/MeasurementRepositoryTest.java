package at.htl.repository;

import at.htl.entity.Measurement;
import at.htl.entity.Sensor;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@QuarkusTest

public class MeasurementRepositoryTest {

    @Inject
    MeasurementRepository repository;

    @Inject
    EntityManager entityManager;

    Sensor sensor1;
    Sensor sensor2;

    Measurement measurement1;
    Measurement measurement2;
    Measurement measurement3;
    Measurement measurement4;
    Measurement measurement5;

    private final String tableName = Measurement.class.getSimpleName();

    @BeforeEach
    @Transactional
    public void setup() {
        clearTable();
        insertTestData();
    }

    private void clearTable() {
        entityManager.createQuery(
                "delete from " + tableName
        ).executeUpdate();
    }

    private void insertTestData() {
        sensor1 = entityManager.merge(new Sensor(
                null,
                null
        ));

        sensor2 = entityManager.merge(new Sensor(
                null,
                null
        ));

        measurement1 = entityManager.merge(new Measurement(
                new Measurement.MeasurementKey(parseTimestamp("2021-04-18"), sensor1),
                10L
        ));

        measurement2 = entityManager.merge(new Measurement(
                new Measurement.MeasurementKey(parseTimestamp("2021-04-17"), sensor1),
                20L
        ));

        measurement3 = entityManager.merge(new Measurement(
                new Measurement.MeasurementKey(parseTimestamp("2021-04-16"), sensor2),
                30L
        ));

        measurement4 = entityManager.merge(new Measurement(
                new Measurement.MeasurementKey(parseTimestamp("2021-04-15"), sensor1),
                40L
        ));

        measurement5 = entityManager.merge(new Measurement(
                new Measurement.MeasurementKey(parseTimestamp("2021-04-14"), sensor2),
                50L
        ));

    }

    private Timestamp parseTimestamp(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return Timestamp.valueOf(localDate.atTime(LocalTime.MIDNIGHT));
    }

    @Test
    public void success_get_from_to() {
        List<Measurement> measurements = repository.get(
                parseTimestamp("2021-04-17"),
                parseTimestamp("2021-04-19")
        );

        assertThat(measurements)
                .asList()
                .hasSize(2)
                .contains(measurement1, measurement2);

        measurements = repository.get(
                parseTimestamp("2021-04-14"),
                parseTimestamp("2021-04-17")
        );

        assertThat(measurements)
                .asList()
                .hasSize(4)
                .contains(measurement2, measurement3, measurement4, measurement5);
    }

    @Test
    public void fail_get_from_to() {
        assertThatThrownBy(() -> repository.get(
                parseTimestamp("2021-04-19"),
                parseTimestamp("2021-04-17")
        )).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void success_get_from_to_sensor() {
        List<Measurement> measurements = repository.get(
                parseTimestamp("2021-04-14"),
                parseTimestamp("2021-04-18"),
                sensor1
        );

        assertThat(measurements)
                .asList()
                .hasSize(3)
                .contains(measurement1, measurement2, measurement4);

        measurements = repository.get(
                parseTimestamp("2021-04-14"),
                parseTimestamp("2021-04-18"),
                sensor2
        );

        assertThat(measurements)
                .asList()
                .hasSize(2)
                .contains(measurement3, measurement5);

        measurements = repository.get(
                parseTimestamp("2021-04-16"),
                parseTimestamp("2021-04-18"),
                sensor2
        );

        assertThat(measurements)
                .asList()
                .hasSize(1)
                .contains(measurement3);
    }

    @Test
    public void fail_get_from_to_sensor() {
        assertThatThrownBy(() -> repository.get(
                parseTimestamp("2021-04-19"),
                parseTimestamp("2021-04-17"),
                sensor1
        )).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> repository.get(
                parseTimestamp("2021-04-19"),
                parseTimestamp("2021-04-17"),
                sensor2
        )).isInstanceOf(IllegalArgumentException.class);
    }

}
