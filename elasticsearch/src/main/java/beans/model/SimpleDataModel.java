package beans.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Indexed(index = "idx_simple_data_model")
public class SimpleDataModel {

    @Id
    private Integer id;

    @GenericField(name = "name", projectable = Projectable.YES)
    private String name;

    @GenericField(name = "content", projectable = Projectable.YES)
    private String content;

}