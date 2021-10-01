package at.htl.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Location location;

    @JsonbTransient
    @OneToMany(mappedBy = "location")
    private List<Thing> thingList;

    private String name;

    public Location(Long id, Location location, String name) {
        this();
        this.id = id;
        this.location = location;
        this.name = name;
    }

    public Location(Location location, String name) {
        this();
        this.location = location;
        this.name = name;
    }

    public Location() {
        this.thingList = new LinkedList<>();
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

    public List<Thing> getThingList() {
        return thingList;
    }

    public void setThingList(List<Thing> thingList) {
        this.thingList = thingList;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", location=" + location +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location1 = (Location) o;
        return Objects.equals(id, location1.id) && Objects.equals(location, location1.location) && Objects.equals(thingList, location1.thingList) && Objects.equals(name, location1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, thingList, name);
    }
}
