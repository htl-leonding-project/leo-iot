package at.htl.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Message implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language languageId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String messageName;
    private String level;
    private String title;
    private String description;

    public Message() {
    }

    public Message(Long id, String messageName, String level, String title, String description) {
        this.id = id;
        this.messageName = messageName;
        this.level = level;
        this.title = title;
        this.description = description;
    }


    public Message(Long id, Language languageId, String messageName, String level, String title, String description) {
        this.id = id;
        this.languageId = languageId;
        this.messageName = messageName;
        this.level = level;
        this.title = title;
        this.description = description;
    }



    // region Getter and Setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String descritption) {
        this.description = descritption;
    }
    // endregion


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && Objects.equals(languageId, message.languageId) && Objects.equals(messageName, message.messageName) && Objects.equals(level, message.level) && Objects.equals(title, message.title) && Objects.equals(description, message.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, languageId, messageName, level, title, description);
    }
}

