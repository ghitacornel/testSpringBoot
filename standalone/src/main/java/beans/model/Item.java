package beans.model;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
public class Item {

    @Id
    private Integer id;

    private String name;
    private Integer length;

}
