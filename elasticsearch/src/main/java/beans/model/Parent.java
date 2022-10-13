package beans.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Indexed(index = "idx_parent")
public class Parent {

    @Id
    private Integer id;

    @GenericField(name = "name", projectable = Projectable.YES)
    private String name;

    @GenericField(name = "content", projectable = Projectable.YES)
    private String content;

    @GenericField(name = "status", projectable = Projectable.YES)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    final private List<Child> children = new ArrayList<>();

}
