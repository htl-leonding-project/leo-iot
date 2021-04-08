package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class _Language {

    @Id
    private Long id;

    public _Language() {
    }

    public _Language(Long id) {
        this.id = id;
    }
    //region Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    //
}
