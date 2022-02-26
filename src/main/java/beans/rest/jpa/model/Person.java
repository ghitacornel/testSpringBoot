package beans.rest.jpa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Person.findByPassword", query = "select t from Person t where t.password = :password")
@Data
@EqualsAndHashCode(of = "id")
public class Person {

    @Id
    private Integer id;

    private String name;

    @JsonProperty("pass")
    @Column(name = "pass")
    private String password;

}
