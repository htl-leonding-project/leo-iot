package at.htl.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class SensorTranslation implements Serializable{

    @Id
    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensorId;
    @Id
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language languageId;
    private String displayName;
    private String unit;

    public SensorTranslation() {
    }

    public SensorTranslation(Sensor sensorId, Language languageId, String displayName, String unit) {
        this.sensorId = sensorId;
        this.languageId = languageId;
        this.displayName = displayName;
        this.unit = unit;
    }

    // region Getter and Setter

    public Sensor getSensorId() {
        return sensorId;
    }

    public void setSensorId(Sensor sensorId) {
        this.sensorId = sensorId;
    }

    public Language getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Language languageId) {
        this.languageId = languageId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    //endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorTranslation that = (SensorTranslation) o;
        return Objects.equals(sensorId, that.sensorId) && Objects.equals(languageId, that.languageId) && Objects.equals(displayName, that.displayName) && Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensorId, languageId, displayName, unit);
    }
}

