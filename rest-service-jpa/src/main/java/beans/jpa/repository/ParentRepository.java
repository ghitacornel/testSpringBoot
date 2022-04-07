package beans.jpa.repository;

import beans.jpa.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Integer> {
}
