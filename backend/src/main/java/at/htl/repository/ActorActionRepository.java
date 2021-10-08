package at.htl.repository;

import at.htl.entity.ActorAction;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Timestamp;
import java.util.List;

@ApplicationScoped
public class ActorActionRepository extends Repository<ActorAction, ActorAction.ActorActionKey> {

    public List<ActorAction> getActorActionByTimestamp(Timestamp timestamp){
        var query = getEntityManager().createQuery(
                "select a " +
                "from ActorAction a " +
                "where a.actorActionKey.timestamp = :searchedTimestamp",
                ActorAction.class);

        query.setParameter("searchedTimestamp", timestamp);
        return query.getResultList();
    }

}
