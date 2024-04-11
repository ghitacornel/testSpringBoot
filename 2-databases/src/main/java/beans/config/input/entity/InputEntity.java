package beans.config.input.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InputEntity {

    @Id
    private Integer id;

    private String firstName;
    private String lastName;
    private int salary;
    private int age;

}
