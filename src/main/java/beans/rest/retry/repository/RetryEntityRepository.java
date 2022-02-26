package beans.rest.retry.repository;

import beans.rest.retry.entity.RetryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetryEntityRepository extends JpaRepository<RetryEntity, Integer> {
}
