package beans.repositories;

import beans.entities.AuditedPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditedPersonRepository extends JpaRepository<AuditedPerson, Long> {
}
