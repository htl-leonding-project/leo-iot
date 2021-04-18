package at.htl.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Location location;

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

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", location=" + location +
                ", name='" + name + '\'' +
                '}';
    }
}
