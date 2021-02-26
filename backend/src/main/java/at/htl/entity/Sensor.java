package at.htl.entity;

import javax.persistence.*;

@Entity
public class Sensor {

    @Id
    private int sensorId;
    private String sensorType;
    @ManyToOne
    @JoinColumn(name = "positionid")
    private Position positionId;
    private String tag;
    private boolean online;

    public Sensor() {
    }

    public Sensor(int sensorId, String sensorType, Position positionId, String tag, boolean online) {
        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.positionId = positionId;
        this.tag = tag;
        this.online = online;
    }

    //region Getter and Setter
    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public Position getPositionId() {
        return positionId;
    }

    public void setPositionId(Position positionId) {
        this.positionId = positionId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
    //endregion
}

