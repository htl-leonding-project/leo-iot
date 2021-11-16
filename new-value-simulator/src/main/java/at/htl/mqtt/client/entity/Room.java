package at.htl.mqtt.client.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Schema(description = "This is a Room of the HTL")
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonbProperty("room_name")
    @Column(name = "room_name")
    @Schema(required = true)
    private String name;

    public Room(String name) {
        this.name = name;
    }

    public Room() {

    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
