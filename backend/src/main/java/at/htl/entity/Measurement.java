package at.htl.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Measurement implements Serializable {

    @Id
    private Timestamp time;
    @Id
    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;
    private Float measurement;

    public Measurement() {
    }

    public Measurement(Timestamp time, Float measurement) {
        this.time = time;
        this.measurement = measurement;
    }

    //region Getter and Setter
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensorId) {
        this.sensor = sensorId;
    }

    public Float getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Float measurement) {
        this.measurement = measurement;
    }
    //endregion


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return Objects.equals(time, that.time) && Objects.equals(sensor, that.sensor) && Objects.equals(measurement, that.measurement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, sensor, measurement);
    }
}
