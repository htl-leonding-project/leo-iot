package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
public class Measurement {
    @Id
    private LocalDateTime timeStamp;
    private String sensor;
    private int value;
    private String room;

    public Measurement(LocalDateTime timeStamp, String sensor, int value, String room) {
        this.timeStamp = timeStamp;
        this.sensor = sensor;
        this.value = value;
        this.room = room;
    }

    public Measurement() {
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "timeStamp=" + timeStamp +
                ", sensor='" + sensor + '\'' +
                ", value=" + value +
                ", room='" + room + '\'' +
                '}';
    }
}
