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
}
