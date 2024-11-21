package beans.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@Document(indexName = "idx_sdm")
public class SimpleDataModel {

    @Field
    private Integer id;

    @Field
    private String name;

    @Field
    private String content;

}
