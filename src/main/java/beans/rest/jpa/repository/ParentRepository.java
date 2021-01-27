package beans.rest.jpa.repository;

import beans.rest.jpa.model.Parent;
import beans.rest.jpa.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Integer> {

}
