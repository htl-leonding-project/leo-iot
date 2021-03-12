package at.htl.control;

import at.htl.entity.Language;
import at.htl.entity.Message;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class InitBean {

    @Inject
    EntityManager em;

    @Inject
    Repository repository;

    @Transactional
    void onStart(@Observes StartupEvent ev) {

        Language language = em.merge(new Language(
                "english"
        ));

        em.merge(new Message(
                2011L,
                language,
                "some String",
                "some String 2",
                "some String 3",
                "some String 4"
        ));
    }
}
