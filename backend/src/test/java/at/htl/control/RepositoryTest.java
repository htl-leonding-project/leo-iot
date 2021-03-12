package at.htl.control;

import at.htl.entity.Language;
import at.htl.entity.Message;
import at.htl.entity.Position;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
    LanguageRepository languageRepository;

    @Inject
    MessageRepository messageRepository;

    @Inject
    AgroalDataSource dataSource;

    private final String tableName = Language.class.getSimpleName();

    Table table;

    @BeforeEach
    @Transactional
    public void setup() {
        languageRepository.deleteAll();

        List<Language> languageList = new ArrayList<>() {{
           add(new Language(
                   0,
                   "english"
           ));

           add(new Language(
                   1,
                   "german"
           ));

           add(new Language(
                   2,
                   "italiano"
           ));
        }};

        languageList.forEach(languageRepository
                .getEntityManager()::merge);
    }

    @Test
    public void test01_language_save() {
        Language language = new Language(
                3,
                "japanese"
        );

        languageRepository.save(language);

        System.out.println(tableName);
        Table table = new Table(dataSource, tableName);
        output(table).toConsole();

        assertThat(table)
                .hasNumberOfRows(4)
                .row(3)
                .value("LANGUAGENAME")
                .isEqualTo("japanese");
    }

    @Test
    public void test02_language_removeById() {
        table = new Table(dataSource, tableName);

        output(table).toConsole();
        assertThat(table)
                .hasNumberOfRows(3)
                .row(0).value("LANGUAGENAME").isEqualTo("english")
                .row(1).value("LANGUAGENAME").isEqualTo("german")
                .row(2).value("LANGUAGENAME").isEqualTo("italiano");

        System.out.println(languageRepository.removeById(0));

        table = new Table(dataSource, tableName);
        output(table).toConsole();
        assertThat(table)
                .hasNumberOfRows(2)
                .row(0).value("LANGUAGENAME").isEqualTo("german")
                .row(1).value("LANGUAGENAME").isEqualTo("italiano");
    }

    @Test
    public void test03_language_remove() {
        table = new Table(dataSource, tableName);

        output(table).toConsole();
        assertThat(table)
                .hasNumberOfRows(3)
                .row(0).value("LANGUAGENAME").isEqualTo("english")
                .row(1).value("LANGUAGENAME").isEqualTo("german")
                .row(2).value("LANGUAGENAME").isEqualTo("italiano");

        Language language = languageRepository.findById(0);
        languageRepository.removeById(0);

        table = new Table(dataSource, tableName);
        output(table).toConsole();
        assertThat(table)
                .hasNumberOfRows(2)
                .row(0).value("LANGUAGENAME").isEqualTo("german")
                .row(1).value("LANGUAGENAME").isEqualTo("italiano");
    }
}
