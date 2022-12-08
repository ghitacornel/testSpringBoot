package beans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import beans.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
