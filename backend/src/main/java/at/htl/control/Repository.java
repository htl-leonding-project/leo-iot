package at.htl.control;

import at.htl.entity.Language;
import at.htl.entity.Message;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class Repository implements PanacheRepositoryBase<Message, Message.IdKey> {


    public Message get(Message.IdKey id) {
        Language language = getEntityManager().find(Language.class, 0L);
        return findById(new Message.IdKey(
                0L,
                language,
                 "some String"
        ));
    }
}
