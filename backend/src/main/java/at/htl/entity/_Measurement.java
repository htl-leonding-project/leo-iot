package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class _Measurement implements Serializable {
    @Id
    private Timestamp timestamp;
    private Float measurement;
    @Id
    @ManyToOne
    @JoinColumn(name = "devicetype")
    private _Device deviceType;

    public _Measurement(Timestamp timestamp, Float measurement, _Device deviceType) {
        this.timestamp = timestamp;
        this.measurement = measurement;
        this.deviceType = deviceType;
    }

    public _Measurement() {
    }

    //region Getter and Setter
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Float getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Float measurement) {
        this.measurement = measurement;
    }

    public _Device getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(_Device deviceType) {
        this.deviceType = deviceType;
    }
    //
}
