package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Language {

    @Id
    private int languageId;
    private String languageName;

    public Language(int languageId, String languageName) {
        this.languageId = languageId;
        this.languageName = languageName;
    }

    public Language() {
    }

    //region Getter and Setter
    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
    //endregion
}

