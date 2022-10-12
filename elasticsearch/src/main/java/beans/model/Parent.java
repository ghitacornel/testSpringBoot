package beans.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Normalizer;

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

    @Field
    private String name;

    @Field(name = "content", analyzer = @Analyzer(definition = "stop"))
    private String content;

    @Field(normalizer = @Normalizer(definition = "lowercase"))
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    final private List<Child> children = new ArrayList<>();

}
