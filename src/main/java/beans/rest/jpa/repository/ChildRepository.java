package beans.rest.jpa.repository;

import beans.rest.jpa.model.Child;
import beans.rest.jpa.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends JpaRepository<Child, Integer> {

}
