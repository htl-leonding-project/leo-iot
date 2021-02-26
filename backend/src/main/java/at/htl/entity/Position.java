package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Position {
    @Id
    private int positionId;

    public Position() {
    }

    public Position(int positionId) {
        this.positionId = positionId;
    }

    //region Getter and Setter

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    //endregion
}

