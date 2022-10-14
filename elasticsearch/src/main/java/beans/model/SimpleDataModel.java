package beans.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Indexed(index = "idx_sdm")
public class SimpleDataModel {

    @Id
    @GenericField(name = "id", projectable = Projectable.YES)
    private Integer id;

    @FullTextField
    private String name;

    @FullTextField
    private String content;

}
