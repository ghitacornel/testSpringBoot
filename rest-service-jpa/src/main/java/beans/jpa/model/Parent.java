package beans.jpa.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
