package at.htl.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
public class Thing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Location location;

    @JsonbTransient
    @OneToMany(mappedBy = "thing")
    private List<Actor> actorList;

    @JsonbTransient
    @OneToMany(mappedBy = "thing")
    private List<Sensor> sensorList;

    private String name;

    public Thing(){
        this.actorList = new LinkedList<>();
        this.sensorList = new LinkedList<>();
    }

    public Thing(Long id, Location location, String name) {
        this();
        this.id = id;
        this.location = location;
        this.name = name;
    }

    public Thing(Location location, String name) {
        this();
        this.location = location;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }

    public List<Sensor> getSensorList() {
        return sensorList;
    }

    public void setSensorList(List<Sensor> sensorList) {
        this.sensorList = sensorList;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "id=" + id +
                ", location=" + location +
                ", actorList=" + actorList +
                ", sensorList=" + sensorList +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Thing)) return false;
        Thing thing = (Thing) o;
        return Objects.equals(id, thing.id) && Objects.equals(location, thing.location) && Objects.equals(actorList, thing.actorList) && Objects.equals(sensorList, thing.sensorList) && Objects.equals(name, thing.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, actorList, sensorList, name);
    }
}
