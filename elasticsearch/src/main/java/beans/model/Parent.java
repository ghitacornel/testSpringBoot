package beans.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Document(indexName = "idx_parent")
public class Parent {

    @Id
    @Field(name = "id")
    private Integer id;

    @Field(name = "name")
    private String name;

    @Field(name = "content")
    private String content;

    @Field(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Field(name = "children", type = FieldType.Nested, includeInParent = true)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    final private List<Child> children = new ArrayList<>();

}
