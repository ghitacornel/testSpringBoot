package beans.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
public class SimpleDataModel {

    @Id
    private Integer id;

    private String name;

    private String content;

}
