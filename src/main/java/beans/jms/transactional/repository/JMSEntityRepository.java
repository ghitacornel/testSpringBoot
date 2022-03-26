package beans.jms.transactional.repository;

import beans.jms.transactional.repository.entity.JMSEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JMSEntityRepository extends JpaRepository<JMSEntity, Integer> {
}
