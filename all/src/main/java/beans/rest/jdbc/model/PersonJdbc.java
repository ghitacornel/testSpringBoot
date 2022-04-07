package beans.rest.jdbc.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class PersonJdbc {

    private Integer id;
    private String name;
    private String pass;

}
