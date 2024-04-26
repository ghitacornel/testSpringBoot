package beans.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Audited
public class Person {

    @Id
    private Integer id;

    private String name;

    private Double salary;

    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;

}
