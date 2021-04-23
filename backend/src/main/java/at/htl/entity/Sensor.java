package at.htl.entity;

import io.vertx.lang.axle.Gen;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Thing thing;

    @ManyToOne
    private SensorType sensorType;

    public Sensor() {
    }

    public Sensor(Thing thing, SensorType sensorType) {
        this.thing = thing;
        this.sensorType = sensorType;
    }

    public Sensor(Long id, Thing thing, SensorType sensorType) {
        this.id = id;
        this.thing = thing;
        this.sensorType = sensorType;
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

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", thing=" + thing +
                ", sensorType=" + sensorType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sensor)) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(id, sensor.id) && Objects.equals(thing, sensor.thing) && Objects.equals(sensorType, sensor.sensorType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, thing, sensorType);
    }
}
