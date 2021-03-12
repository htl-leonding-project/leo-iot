package at.htl.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Message{

    @Embeddable
    public static class MessageId implements Serializable {

        @NotNull
        private Long id;

        @ManyToOne
        @JoinColumn(name = "language_id")
        private Language language;

        private String messageName;

        public MessageId(Language language, String name) {
            this.language = language;
            this.messageName = name;
        }

        public MessageId(Long id, Language language, String name) {
            this.id = id;
            this.language = language;
            this.messageName = name;
        }

        public MessageId() {

        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Language getLanguage() {
            return language;
        }

        public void setLanguage(Language language) {
            this.language = language;
        }

        public String getMessageName() {
            return messageName;
        }

        public void setMessageName(String messageName) {
            this.messageName = messageName;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public String toString() {
            return "MessageId{" +
                    "id=" + id +
                    ", language=" + language +
                    ", messageName='" + messageName + '\'' +
                    '}';
        }
    }

    @EmbeddedId
    private MessageId id;
    private String level;
    private String title;
    private String description;

    public Message() {
    }

    public Message(MessageId id, String level, String title, String description) {
        this.id = id;
        this.level = level;
        this.title = title;
        this.description = description;
    }

    public Message(Language language, String name, String level, String title, String description) {
        this.id = new MessageId(language, name);
        this.level = level;
        this.title = title;
        this.description = description;
    }

    public Message(Long id, Language language, String name, String level, String title, String description) {
        this.id = new MessageId(id, language, name);
        this.level = level;
        this.title = title;
        this.description = description;
    }

    // region Getter and Setter

    public MessageId getId() {
        return id;
    }

    public void setId(MessageId id) {
        this.id = id;
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

    public void setDescription(String description) {
        this.description = description;
    }


    // endregion

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) && Objects.equals(level, message.level) && Objects.equals(title, message.title) && Objects.equals(description, message.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, level, title, description);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", level='" + level + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

