package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class _Location {
    @Id
    private String id;
    @ManyToOne
    private _Location location;

    public _Location() {
    }

    public _Location(String id, _Location location) {
        this.id = id;
        this.location = location;
    }

    //region Getter and Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public _Location getLocation() {
        return location;
    }

    public void setLocation(_Location location) {
        this.location = location;
    }

    //

}
