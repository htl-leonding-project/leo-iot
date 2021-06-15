package at.htl.control;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class InitBean {

    @Inject
    EntityManager em;

    @Inject
    MqttController controller;

    @Transactional
    void onStart(@Observes StartupEvent ev) { }
}
