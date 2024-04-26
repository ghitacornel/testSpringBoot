package beans.services;

import beans.entities.AuditedPerson;
import beans.repositories.AuditedPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)// for test purpose
public class AuditedPersonService {

    private final AuditedPersonRepository repository;

    public void save(AuditedPerson person) {
        repository.save(person);
    }

    public AuditedPerson findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
