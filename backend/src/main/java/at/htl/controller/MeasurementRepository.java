package at.htl.controller;

import at.htl.entity.Measurement;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@ApplicationScoped
public class MeasurementRepository implements PanacheRepositoryBase<Measurement, LocalDateTime> {

    @Transactional
    public Measurement save(Measurement m) {
        return getEntityManager().merge(m);
    }
}
