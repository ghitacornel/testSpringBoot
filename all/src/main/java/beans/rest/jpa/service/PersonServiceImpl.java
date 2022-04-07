package beans.rest.jpa.service;

import beans.rest.jpa.model.Person;
import beans.rest.jpa.repository.CustomPersonRepository;
import beans.rest.jpa.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final CustomPersonRepository customPersonRepository;

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person findById(Integer id) {
        return repository.findById(id).get();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public void save(Person person) {
        repository.save(person);
    }

    public List<Person> findByPassword(String password) {
        return customPersonRepository.findByPassword(password);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}
