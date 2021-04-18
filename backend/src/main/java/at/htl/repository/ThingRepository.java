package at.htl.repository;

import at.htl.entity.Thing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ThingRepository extends Repository<Thing, Long> {
}
