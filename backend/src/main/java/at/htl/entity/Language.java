package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String languageName;

    public Language(String languageName) {
        this.languageName = languageName;
    }

    public Language() {
    }

    //region Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
    //endregion


    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", languageName='" + languageName + '\'' +
                '}';
    }
}

