package at.htl.control;

import at.htl.entity.Message;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MessageRepository extends Repository<Message, Integer> {
}
