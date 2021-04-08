package at.htl.test2;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue(value = "sensor")
@PrimaryKeyJoinColumn(name = "id")
public class Sensor2 extends Device2 {

    @Id
    private Long id;

    public SensorType2 sensorType;

    public enum SensorType2 {
        NOISE,
        TEMPERATURE,
        TRAFFICLIGHT,
        HUMIDITY,
        PRESSURE,
        CO2,
        MOTION,
        RSSI,
        NEOPIXEL
    }

    public SensorType2 getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType2 sensorType) {
        this.sensorType = sensorType;
    }
}

