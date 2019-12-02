package beans.rest.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class User {

    private Integer id;
    private String name;
    private String password;

}
