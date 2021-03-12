package at.htl.entity;

import javax.persistence.*;

@Entity
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sensorType;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position positionId;
    private String tag;
    private boolean online;

    public Sensor() {
    }

    public Sensor(String sensorType, Position positionId, String tag, boolean online) {
        this.sensorType = sensorType;
        this.positionId = positionId;
        this.tag = tag;
        this.online = online;
    }

    //region Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

