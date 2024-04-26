package beans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import beans.entity.Person;
import org.springframework.data.repository.history.RevisionRepository;

public interface PersonRepository extends JpaRepository<Person, Integer>, RevisionRepository<Person, Integer, Integer> {
}
