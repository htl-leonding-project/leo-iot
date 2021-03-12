package at.htl.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class SensorTranslation implements Serializable{

    @Id
    @ManyToOne
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
}

