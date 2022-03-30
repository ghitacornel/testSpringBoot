package beans.transactional.repository.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data// need this for tests
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionalEntity {

    @Id
    private Integer id;

    private String data;

}
