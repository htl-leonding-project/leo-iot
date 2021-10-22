package at.htl.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Schema(description = "contains the value of the active actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(required = true)
    private Long id;

    @ManyToOne
    private Thing thing;

    @ManyToOne
    private ActorType actortype;
    @JsonbProperty("actor_value")
    @Schema(required = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor)) return false;
        Actor actor = (Actor) o;
        return Double.compare(actor.value, value) == 0 && Objects.equals(id, actor.id) && Objects.equals(thing, actor.thing) && Objects.equals(actortype, actor.actortype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, thing, actortype, value);
    }
}
