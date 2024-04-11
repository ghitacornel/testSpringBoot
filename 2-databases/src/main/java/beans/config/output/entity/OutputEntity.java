package beans.config.output.entity;

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
public class OutputEntity {

    @Id
    private Integer id;

    private String firstName;
    private String lastName;
    private int salary;
    private int age;
    private int difference;

}
