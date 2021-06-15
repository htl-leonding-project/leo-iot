package at.htl.repository;

import at.htl.entity.SensorType;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class SensorTypeRepository extends Repository<SensorType, Long> {
    public SensorType getOrCreateByName(String name) {
        return getByName(name)
                .orElseGet(() -> save(new SensorType(
                        name,
                        null
                )));
    }

    public Optional<SensorType> getByName(String name) {
        return find("name", name)
                .firstResultOptional();
    }
}
