package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SensorTranslation {

    @Id
    private int sensorId;
    @Id
    private int languageId;

    private String displayName;
    private String unit;

    public SensorTranslation() {
    }

    public SensorTranslation(int sensorId, int languageId, String displayName, String unit) {
        this.sensorId = sensorId;
        this.languageId = languageId;
        this.displayName = displayName;
        this.unit = unit;
    }

    // region Getter and Setter
    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
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
