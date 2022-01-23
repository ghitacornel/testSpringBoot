package beans.rest.jpa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Item")
@Data
@EqualsAndHashCode(of = "id")
public class Item {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "length")
    private Integer length;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "registration")
    private LocalDateTime registration;

    @Column(name = "fake")
    private Boolean fake;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    public enum State {
        NEW, USED, RETIRED
    }

}
