package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Position() {
    }

    //region Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long positionId) {
        this.id = positionId;
    }

    //endregion


    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                '}';
    }
}

