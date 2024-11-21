package beans.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Document(indexName = "idx_parent")
public class ParentELK {

    @Field
    private Integer id;

    @Field
    private String name;

    @Field
    private String content;

    @Field(name = "children", type = FieldType.Nested, includeInParent = true)
    final private List<ChildELK> children = new ArrayList<>();

}
