package beans.jpa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Parent {

    @Id
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "parent")
    final private List<Child> children = new ArrayList<>();

}
