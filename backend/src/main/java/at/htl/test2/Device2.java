package at.htl.test2;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "device_type", discriminatorType = DiscriminatorType.STRING)
public class Device2 {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device2 parentDevice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
