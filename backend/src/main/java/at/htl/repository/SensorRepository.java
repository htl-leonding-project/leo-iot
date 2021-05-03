package at.htl.repository;

import at.htl.entity.Sensor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SensorRepository extends Repository<Sensor, Long> {
}
