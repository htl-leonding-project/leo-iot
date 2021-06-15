package at.htl.repository;

import at.htl.entity.Location;
import at.htl.entity.Thing;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class ThingRepository extends Repository<Thing, Long> {

    public Thing getOrCreateByTree(String name, Location location) {
        return getThingByNameAndLocation(name, location)
                .orElseGet(() -> save(new Thing(
                        location,
                        name
                )));
    }

    private Optional<Thing> getThingByNameAndLocation(String name, Location location) {
        TypedQuery<Thing> query;
        if (location != null) {
            query = getEntityManager().createQuery(
                    "select t from Thing t where t.name = :name and t.location = :location",
                    Thing.class
            );

            query.setParameter("location", location);
        } else {
            query = getEntityManager().createQuery(
                    "select t from Thing t where t.name = :name and t.location is null",
                    Thing.class
            );
        }

        query.setParameter("name", name);

        return query
                .getResultStream()
                .findFirst();
    }
}
