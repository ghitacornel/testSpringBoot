package beans.service;

import beans.entity.Person;
import beans.entity.Status;
import beans.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonService {

    private final PersonRepository repository;

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Person person) {
        repository.save(person);
    }

    public void duplicateName(Integer id) {
        Person person = repository.getReferenceById(id);
        person.setName(person.getName() + " " + person.getName());
    }

    public void duplicateSalary(Integer id) {
        Person person = repository.getReferenceById(id);
        person.setSalary(2 * person.getSalary());
    }

    public void makeBusy(Integer id) {
        Person person = repository.getReferenceById(id);
        person.setStatus(Status.BUSY);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
