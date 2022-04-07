package beans.jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
