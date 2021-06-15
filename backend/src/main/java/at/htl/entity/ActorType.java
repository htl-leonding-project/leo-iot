package at.htl.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ActorType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Unit unit;

    public ActorType() {
    }

    public ActorType(String name, Unit unit) {
        this.name = name;
        this.unit = unit;
    }

    public ActorType(Long id, String name, Unit unit) {
        this.id = id;
        this.name = name;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "ActorType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit=" + unit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActorType)) return false;
        ActorType actorType = (ActorType) o;
        return Objects.equals(id, actorType.id) && Objects.equals(name, actorType.name) && Objects.equals(unit, actorType.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unit);
    }
}
