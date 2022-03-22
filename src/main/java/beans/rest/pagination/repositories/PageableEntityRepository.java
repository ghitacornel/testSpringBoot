package beans.rest.pagination.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PageableEntityRepository extends JpaRepository<PageableEntity, Integer> {
}