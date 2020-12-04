package beans.rest.jpa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "person")
@Data
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@NamedQuery(name = "Person.findByPassword", query = "select t from Person t where t.password = :password")
public class Person {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "pass")
    private String password;

}
