package beans.config.input.repository;

import beans.config.input.entity.InputEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputEntityRepository extends JpaRepository<InputEntity, Integer> {
}
