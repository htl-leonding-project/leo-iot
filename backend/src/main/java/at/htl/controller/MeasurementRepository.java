package at.htl.controller;

import at.htl.entity.Measurement;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.quarkus.runtime.Quarkus;
import org.hibernate.reactive.mutiny.Mutiny;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@ApplicationScoped
public class MeasurementRepository implements PanacheRepository<Measurement> {

    @Inject
    Mutiny.Session ms;

    public void save(Measurement measurement) {
        ms.persist(measurement)
                .chain(ms::flush);
    }
}
