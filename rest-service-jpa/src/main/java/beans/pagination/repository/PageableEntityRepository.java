package beans.pagination.repository;

import beans.pagination.repository.entity.PageableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageableEntityRepository extends JpaRepository<PageableEntity, Integer> {
}