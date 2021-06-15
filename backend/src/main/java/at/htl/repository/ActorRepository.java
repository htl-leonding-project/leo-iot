package at.htl.repository;

import at.htl.entity.Actor;
import at.htl.entity.ActorAction;
import at.htl.entity.ActorType;
import at.htl.entity.Thing;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class ActorRepository extends Repository<Actor, Long> {

    public Actor getOrCreateActorByTree(ActorType actorType, Thing thing, double value) {
        var query = getEntityManager().createQuery(
                "select a from Actor a where a.thing = :thing and a.actortype = :actorType",
                Actor.class
        );

        query.setParameter("thing", thing);
        query.setParameter("actorType", actorType);

        return query
                .getResultStream()
                .findFirst()
                .orElseGet(() -> save(new Actor(
                        thing,
                        actorType,
                        value
                )));
    }
}
