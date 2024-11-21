package beans.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Entity
@Getter
@Setter
@Document(indexName = "idx_child")
public class Child {

    @Id
    @Field(name = "id")
    private Integer id;

    @Field(name = "name")
    private String name;

    @Field(name = "content")
    private String content;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Parent parent;

}
