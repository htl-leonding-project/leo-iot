package at.htl.repository;

import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

@QuarkusTest
public class RepositoryTest {

    @Inject
    TestEntityRepository repository;

    @Inject
    EntityManager entityManager;

    @Inject
    AgroalDataSource dataSource;

    private TestEntity testEntity1;
    private TestEntity testEntity2;
    private TestEntity testEntity3;

    private final String testEntityTableName = TestEntity.class.getSimpleName();

    Table table;

    @BeforeEach
    @Transactional
    public void setup() {
        clearTable();
        setupTestEntity();
    }

    public void clearTable() {
        entityManager.createQuery(
                "delete from " + testEntityTableName
        ).executeUpdate();
    }

    private void setupTestEntity() {

        testEntity1 = entityManager.merge(new TestEntity(
                1L,
                "testEntity1",
                10
        ));

        testEntity2 = entityManager.merge(new TestEntity(
                2L,
                "testEntity2",
                20
        ));

        testEntity3 = entityManager.merge(new TestEntity(
                3L,
                "testEntity3",
                30
        ));
    }

    @Test
    public void testEntity_checkData() {
        table = new Table(dataSource, testEntityTableName);
        output(table).toConsole();

        assertThat(table)
                .hasNumberOfRows(3)
                .row(0).value("text").isEqualTo(testEntity1.text)
                .row(1).value("text").isEqualTo(testEntity2.text)
                .row(2).value("text").isEqualTo(testEntity3.text);
    }

    @Test
    public void testEntity_save() {
        TestEntity testEntity4 = new TestEntity(
                4L,
                "testEntity4",
                40
        );

        testEntity4 = repository.save(testEntity4);

        table = new Table(dataSource, testEntityTableName);
        output(table).toConsole();

        assertThat(table)
                .hasNumberOfRows(4)
                .row(3)
                    .value("text").isEqualTo(testEntity4.text)
                    .value("value").isEqualTo(testEntity4.value);
    }

    @Test
    public void testEntity_removeById_success() {
        assertThat(repository.removeById(testEntity3.id))
                .isTrue();

        table = new Table(dataSource, testEntityTableName);
        output(table).toConsole();

        assertThat(table)
                .hasNumberOfRows(2)
                .row(0).value("text").isEqualTo(testEntity1.text)
                .row(1).value("text").isEqualTo(testEntity2.text);
    }

    @Test
    public void thing_removeById_fail() {
        assertThat(repository.removeById(50L))
                .isFalse();
    }

}

@Entity
class TestEntity {
    @Id
    public Long id;
    public String text;
    public int value;

    public TestEntity(Long id, String text, int value) {
        this(text, value);
        this.id = id;
    }

    public TestEntity(String text, int value) {
        this.text = text;
        this.value = value;
    }


    public TestEntity() {}
}

@ApplicationScoped
class TestEntityRepository extends Repository<TestEntity, Long> { }
