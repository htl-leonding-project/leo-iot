package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class _Word implements Serializable {
    @Id
    private String word;
    @Id
    @ManyToOne
    @JoinColumn(name = "languageid")
    private _Language languageType;

    public _Word() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public _Language getLanguageType() {
        return languageType;
    }

    public void setLanguageType(_Language languageType) {
        this.languageType = languageType;
    }
}
