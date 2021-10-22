
package at.htl.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Schema(description = "contains the unit type/symbol of the sensortype")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonbProperty("symbol")
    private String symbol;
    @JsonbTransient
    @OneToMany(mappedBy = "unit")
    private List<SensorType> sensorTypeList;
    @JsonbTransient
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;
        Unit unit = (Unit) o;
        return Objects.equals(id, unit.id) && Objects.equals(symbol, unit.symbol) && Objects.equals(sensorTypeList, unit.sensorTypeList) && Objects.equals(actorTypeList, unit.actorTypeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol, sensorTypeList, actorTypeList);
    }
}


