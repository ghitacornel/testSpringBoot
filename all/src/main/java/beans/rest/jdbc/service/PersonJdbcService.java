package beans.rest.jdbc.service;

import beans.aop.LogExecution;
import beans.aop.LogExecutionTime;
import beans.rest.jdbc.model.PersonJdbc;
import beans.rest.jdbc.repository.PersonJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@LogExecution
@Service
public class PersonJdbcService {

    final private PersonJdbcRepository repository;

    public PersonJdbcService(PersonJdbcRepository repository) {
        this.repository = repository;
    }

    @LogExecutionTime
    public List<PersonJdbc> findAll() {
        return repository.findAll();
    }

    public PersonJdbc findById(Integer id) {
        return repository.findById(id);
    }

    public void create(PersonJdbc personJDBC) {
        repository.create(personJDBC);
    }

    public void removeById(Integer id) {
        repository.removeById(id);
    }

    public void update(PersonJdbc personJDBC) {
        repository.update(personJDBC);
    }
}
