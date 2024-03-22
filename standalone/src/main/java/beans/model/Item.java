package beans.model;

import lombok.ToString;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@ToString
public class Item {

    @Id
    private Integer id;

    private String name;
    private Integer length;

}
