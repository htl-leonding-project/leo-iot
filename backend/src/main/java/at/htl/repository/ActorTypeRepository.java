package at.htl.repository;

import at.htl.entity.ActorType;

import javax.enterprise.context.ApplicationScoped;
import javax.swing.text.html.Option;
import java.util.Optional;

@ApplicationScoped
public class ActorTypeRepository extends Repository<ActorType, Long> {

    public Optional<ActorType> getByName(String name) {
        return find("name", name)
                .firstResultOptional();
    }
}
