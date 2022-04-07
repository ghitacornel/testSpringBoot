package beans.retry.repository;

import beans.retry.entity.RetryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetryEntityRepository extends JpaRepository<RetryEntity, Integer> {
}
