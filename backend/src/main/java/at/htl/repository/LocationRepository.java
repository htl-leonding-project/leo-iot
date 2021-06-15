package at.htl.repository;

import at.htl.entity.Location;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class LocationRepository extends Repository<Location, Long> {

    public Location getLocationByTree(String... locationStrings) {
        Location lastLocation = null;
        Location location = null;

        for (int i = 0; i < locationStrings.length; i++) {
            if (i == 0) {
                location = getOrCreateByParentLocationAndName(
                        null,
                        locationStrings[i]
                );
            } else {
                location = getOrCreateByParentLocationAndName(
                        lastLocation,
                        locationStrings[i]
                );
            }

            lastLocation = location;
        }


        return location;
    }

    public Location getOrCreateByParentLocationAndName(Location parentLocation, String name) {
        return getLocationByParentLocationAndName(parentLocation, name)
                .orElseGet(() -> save(new Location(
                        parentLocation,
                        name
                )));
    }

    public Optional<Location> getLocationByParentLocationAndName(Location parentLocation, String name) {
        TypedQuery<Location> query;

        if (parentLocation != null) {
            query = getEntityManager().createQuery(
                    "select l from Location l where l.location = :parentLocation and l.name = :name",
                    Location.class
            );

            query.setParameter("parentLocation", parentLocation);
        } else  {
            query = getEntityManager().createQuery(
                    "select l from Location l where l.location is null and l.name = :name",
                    Location.class
            );
        }

        query.setParameter("name", name);

        return query
                .getResultStream()
                .findFirst();
    }
}
