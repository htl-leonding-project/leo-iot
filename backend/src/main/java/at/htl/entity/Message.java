package at.htl.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Message implements Serializable {
    
    @Id
    private int messageId;
    @Id
    @ManyToOne
    private Language languageId;
    @Id
    private String messageName;
    private String level;
    private String title;
    private String descritption;

    public Message() {
    }

    public Message(int messageId, Language languageId, String messageName, String level, String title, String descritption) {
        this.messageId = messageId;
        this.languageId = languageId;
        this.messageName = messageName;
        this.level = level;
        this.title = title;
        this.descritption = descritption;
    }

    // region Getter and Setter
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public Language getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Language languageId) {
        this.languageId = languageId;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescritption() {
        return descritption;
    }

    public void setDescritption(String descritption) {
        this.descritption = descritption;
    }
    // endregion
}

