package at.htl.repository;

import at.htl.entity.Location;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
public class LocationRepository extends Repository<Location, Long> {

    public Location getLocationByParentLocationAndName(Location parentLocation, String name) {
        System.out.println("parentLocatio: " + parentLocation);
        System.out.println("name: " + name);
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
                .findFirst()
                .orElse(null);
    }
}
