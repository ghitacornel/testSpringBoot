package beans.jpa.service;

import beans.jpa.model.Person;
import beans.jpa.repository.CustomPersonRepository;
import beans.jpa.repository.PersonRepository;
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
