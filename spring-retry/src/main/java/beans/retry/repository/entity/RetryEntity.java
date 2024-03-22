package beans.retry.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
@Data// for test purpose
@NoArgsConstructor
@AllArgsConstructor
public class RetryEntity {

    @Id
    private Integer id;

    private String name;

}
