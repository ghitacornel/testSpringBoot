package beans.retry.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data// for test purpose
@NoArgsConstructor
@AllArgsConstructor
public class RetryEntity {

    @Id
    private Integer id;

    private String name;

}
