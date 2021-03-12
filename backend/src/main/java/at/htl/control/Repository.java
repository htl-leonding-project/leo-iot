package at.htl.control;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

public abstract class Repository<Entity, Identification> implements PanacheRepositoryBase<Entity, Identification> {

    @Transactional
    public Entity save(Entity entity) {
        return getEntityManager().merge(entity);
    }

    @Transactional
    public boolean removeById(Identification id) {
        return deleteById(id);
    }

    @Transactional
    public void remove(Entity entity) {
        delete(entity);
    }
}
