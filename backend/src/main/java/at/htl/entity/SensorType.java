package at.htl.entity;

import javax.persistence.*;

@Entity
public class SensorType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
