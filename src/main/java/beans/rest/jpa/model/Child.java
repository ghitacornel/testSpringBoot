package beans.rest.jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Child {

    @Id
    private Integer id;

    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Parent parent;

}
