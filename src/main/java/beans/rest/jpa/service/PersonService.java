package beans.rest.jpa.service;

import beans.rest.jpa.model.Person;
import beans.rest.jpa.repository.CustomPersonRepository;
import beans.rest.jpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private CustomPersonRepository customPersonRepository;

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
}
