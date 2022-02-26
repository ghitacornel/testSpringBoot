package beans.rest.jpa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Parent {

    @Id
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    final private List<Child> children = new ArrayList<>();

}
