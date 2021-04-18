package at.htl.repository;

import at.htl.entity.Measurement;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MeasurementRepository extends Repository<Measurement, Measurement.MeasurementKey> {
}
