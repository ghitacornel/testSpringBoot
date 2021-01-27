package beans.rest.jdbc.model;

import lombok.*;

@Data
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class PersonJDBC {

    private Integer id;
    private String name;
    private String password;

}
