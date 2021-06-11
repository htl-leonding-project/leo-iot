package at.htl.repository;

import at.htl.entity.SensorType;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SensorTypeRepository extends Repository<SensorType, Long> {
    public SensorType getOrCreateByName(String name) {
        return find("name", name)
                .firstResultOptional()
                .orElse(save(new SensorType(
                        name,
                        null
                )));
    }
}
