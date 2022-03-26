package beans.jms.transactional.repository.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JMSEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JMSEntity {

    @Id
    private Integer id;

    private String message;

}
