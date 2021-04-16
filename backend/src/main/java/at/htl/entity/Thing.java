package at.htl.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Thing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Location location;

    @OneToMany(mappedBy = "thing")
    private List<Actor> actorList;

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
}
