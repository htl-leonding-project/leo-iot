package at.htl.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class _Device {
    @Id
    private Long id;
    private String deviceType;
    @OneToMany
    private List<_Measurement> measurements;
    @ManyToOne
    private _Device device;
    @OneToOne
    @JoinColumn(name = "location")
    private _Location location;

    public _Device() {
    }

    public _Device(Long id, String deviceType, List<_Measurement> measurements, _Device device, _Location location) {
        this.id = id;
        this.deviceType = deviceType;
        this.measurements = measurements;
        this.device = device;
        this.location = location;
    }

    //region Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public List<_Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<_Measurement> measurements) {
        this.measurements = measurements;
    }

    public _Device getDevice() {
        return device;
    }

    public void setDevice(_Device device) {
        this.device = device;
    }

    public _Location getLocation() {
        return location;
    }

    public void setLocation(_Location location) {
        this.location = location;
    }
    //endregion
}
