package beans.jpa.service;

import beans.jpa.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    Person findById(Integer id);

    void deleteById(Integer id);

    void save(Person person);

    List<Person> findByPassword(String password);

    void deleteAll();

}
