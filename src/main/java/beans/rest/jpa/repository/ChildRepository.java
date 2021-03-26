package beans.rest.jpa.repository;

import beans.rest.jpa.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Integer> {
}
