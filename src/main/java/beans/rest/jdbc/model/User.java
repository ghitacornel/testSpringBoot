package beans.rest.jdbc.model;

import lombok.*;

@Data
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class User {

    private Integer id;
    private String name;
    private String password;

}
