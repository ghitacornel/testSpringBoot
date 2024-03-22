package beans.jpa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@NamedQuery(name = "Person.findByPassword", query = "select t from Person t where t.password = :password")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Person {

    @Id
    private Integer id;

    private String name;

    @JsonProperty("pass")
    @Column(name = "pass")
    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfBirth;

    public Person(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

}
