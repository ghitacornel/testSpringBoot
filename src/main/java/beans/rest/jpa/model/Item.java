package beans.rest.jpa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Item {

    @Id
    private Integer id;

    private String name;
    private Integer length;
    private Double weight;
    private LocalDateTime registration;
    private Boolean fake;

    @Enumerated(EnumType.STRING)
    private State state;

    public enum State {
        NEW, USED, RETIRED
    }

}
