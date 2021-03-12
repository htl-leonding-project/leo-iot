package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class PositionTranslation implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position positionId;
    @Id
    @ManyToOne()
    @JoinColumn(name = "language_id")
    private Language languageId;
    private String area;
    private String section;
    private String position;

    public PositionTranslation() {
    }

    public PositionTranslation(Position positionId, Language languageId, String area, String section, String position) {
        this.positionId = positionId;
        this.languageId = languageId;
        this.area = area;
        this.section = section;
        this.position = position;
    }

    //region Getter and Setter

    public Position getPositionId() {
        return positionId;
    }

    public void setPositionId(Position positionId) {
        this.positionId = positionId;
    }

    public Language getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Language languageId) {
        this.languageId = languageId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    //endregion

}
