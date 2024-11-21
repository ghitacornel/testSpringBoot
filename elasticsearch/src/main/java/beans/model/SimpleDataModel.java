package beans.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Entity
@Getter
@Setter
@Document(indexName = "idx_sdm")
public class SimpleDataModel {

    @Id
    @Field(name = "id")
    private Integer id;

    @Field(name = "name")
    private String name;

    @Field(name = "content")
    private String content;

}
