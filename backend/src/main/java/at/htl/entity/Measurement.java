package at.htl.entity;


import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Schema(description = "contains the value of the  measurement")
public class Measurement {

    @EmbeddedId
    private MeasurementKey measurementKey;
    @JsonbProperty("measurement_value")
    @Schema(required = true)
    private double value;

    public Measurement(){

    }

    public Measurement(MeasurementKey measurementKey, double value){
        this.measurementKey = measurementKey;
        this.value = value;
    }

    public MeasurementKey getMeasurementKey() {
        return measurementKey;
    }

    public void setMeasurementKey(MeasurementKey measurementKey) {
        this.measurementKey = measurementKey;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Measurement)) return false;
        Measurement that = (Measurement) o;
        return Double.compare(that.value, value) == 0 && Objects.equals(measurementKey, that.measurementKey);
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "measurementKey=" + measurementKey +
                ", value=" + value +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(measurementKey, value);
    }

    @Embeddable
    public static class MeasurementKey implements Serializable{
        private Timestamp timestamp;
        @ManyToOne
        private Sensor sensor;

        public MeasurementKey(){

        }

        public MeasurementKey(Timestamp timestamp, Sensor sensor){
            this.timestamp = timestamp;
            this.sensor = sensor;
        }

        public Timestamp getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
        }

        public Sensor getSensor() {
            return sensor;
        }

        public void setSensor(Sensor sensor) {
            this.sensor = sensor;
        }

        @Override
        public String toString() {
            return "MeasurementKey{" +
                    "timestamp=" + timestamp +
                    ", sensor=" + sensor +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MeasurementKey that = (MeasurementKey) o;
            return Objects.equals(timestamp, that.timestamp) && Objects.equals(sensor, that.sensor);
        }

        @Override
        public int hashCode() {
            return Objects.hash(timestamp, sensor);
        }
    }

}
