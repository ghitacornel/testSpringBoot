package beans.rest.jpa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "person")
@NamedQuery(name = "Person.findByPassword", query = "select t from Person t where t.password = :password")
@Data
@EqualsAndHashCode(of = "id")
public class Person {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @JsonProperty("pass")
    @Column(name = "pass")
    private String password;

}
