
package at.htl.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symbol;
    @OneToMany(mappedBy = "unit")
    private List<SensorType> sensorTypeList;
    @OneToMany(mappedBy = "unit")
    private List<ActorType> actorTypeList;


    public Unit(){
        this.sensorTypeList = new LinkedList<>();
        this.actorTypeList = new LinkedList<>();
    }

    public Unit(Long id, String symbol) {
        this();
        this.id = id;
        this.symbol = symbol;
    }

    public Unit(String symbol) {
        this();
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<SensorType> getSensorTypeList() {
        return sensorTypeList;
    }

    public void setSensorTypeList(List<SensorType> sensorTypeList) {
        this.sensorTypeList = sensorTypeList;
    }

    public List<ActorType> getActorTypeList() {
        return actorTypeList;
    }

    public void setActorTypeList(List<ActorType> actorTypeList) {
        this.actorTypeList = actorTypeList;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", sensorTypeList=" + sensorTypeList +
                ", actorTypeList=" + actorTypeList +
                '}';
    }
}


