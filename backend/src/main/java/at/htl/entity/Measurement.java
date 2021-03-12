package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Measurement implements Serializable {

    @Id
    private Timestamp time;
    @Id
    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensorId;
    private Float measurement;

    public Measurement() {
    }

    public Measurement(Timestamp time, Sensor sensorId, Float measurement) {
        this.time = time;
        this.sensorId = sensorId;
        this.measurement = measurement;
    }

    //region Getter and Setter
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Sensor getSensorId() {
        return sensorId;
    }

    public void setSensorId(Sensor sensorId) {
        this.sensorId = sensorId;
    }

    public Float getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Float measurement) {
        this.measurement = measurement;
    }
    //endregion
}
