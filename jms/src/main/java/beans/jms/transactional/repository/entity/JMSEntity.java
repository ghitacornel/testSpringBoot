package beans.jms.transactional.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
