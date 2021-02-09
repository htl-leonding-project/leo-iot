package at.htl.controller;

import at.htl.entity.Measurement;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@ApplicationScoped
public class MeasurementRepository implements PanacheRepository<Measurement> {

    @Transactional
    public Measurement save(Measurement measurement) {
        System.out.println("Persisting: " + measurement);

        this.persistAndFlush(measurement);

        return measurement;
    }
}
