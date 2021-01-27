package beans.rest.jpa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parent")
@Data
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class Parent {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    final private List<Child> children = new ArrayList<>();

}
