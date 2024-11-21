package beans.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@AllArgsConstructor
@Document(indexName = "idx_child")
public class ChildELK {

    @Field
    private Integer id;

    @Field
    private String name;

    @Field
    private String content;

}
