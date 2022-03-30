package beans.transactional.repository;

import beans.transactional.repository.entity.TransactionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionalEntityRepository extends JpaRepository<TransactionalEntity, Integer> {
}
