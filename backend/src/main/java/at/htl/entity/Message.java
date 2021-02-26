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

    
}

