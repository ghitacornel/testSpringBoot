package beans.config.output.repository;

import beans.config.output.entity.OutputEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutputEntityRepository extends JpaRepository<OutputEntity, Integer> {
}
