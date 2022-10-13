package beans.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@Indexed(index = "idx_child")
public class Child {

    @Id
    @GenericField(name = "id", projectable = Projectable.YES)
    private Integer id;

    @GenericField(name = "name", projectable = Projectable.YES)
    private String name;

    @GenericField(name = "content", projectable = Projectable.YES)
    private String content;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Parent parent;

}
