package at.htl.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Schema(description = "contains the sensortype of ta sensor")
public class SensorType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonbProperty("senortype")
    private String name;

    @ManyToOne
    private Unit unit;

    public SensorType() { }

    public SensorType(String name, Unit unit) {
        this.name = name;
        this.unit = unit;
    }

    public SensorType(Long id, String name, Unit unit) {
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
        return "SensorType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit=" + unit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SensorType)) return false;
        SensorType that = (SensorType) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unit);
    }
}
