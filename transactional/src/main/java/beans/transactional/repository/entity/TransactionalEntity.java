package beans.transactional.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data// need this for tests
@NoArgsConstructor
@AllArgsConstructor
public class TransactionalEntity {

    @Id
    private Integer id;

    private String data;

}
