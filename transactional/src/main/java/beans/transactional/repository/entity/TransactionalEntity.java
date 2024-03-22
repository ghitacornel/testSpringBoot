package beans.transactional.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Data// need this for tests
@NoArgsConstructor
@AllArgsConstructor
public class TransactionalEntity {

    @Id
    private Integer id;

    private String data;

}
