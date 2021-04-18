package at.htl.entity;

import javax.persistence.*;

@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Thing thing;

    @ManyToOne
    private ActorType actortype;

    private double value;

    public Actor() { }

    public Actor(Long id, Thing thing, ActorType actortype, double value) {
        this.id = id;
        this.thing = thing;
        this.actortype = actortype;
        this.value = value;
    }

    public Actor(Thing thing, ActorType actortype, double value) {
        this.thing = thing;
        this.actortype = actortype;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }

    public ActorType getActortype() {
        return actortype;
    }

    public void setActortype(ActorType actortype) {
        this.actortype = actortype;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", thing=" + thing +
                ", actortype=" + actortype +
                ", value=" + value +
                '}';
    }
}
