package beans.jdbc.service;

import beans.jdbc.model.PersonJdbc;
import beans.jdbc.repository.PersonJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonJdbcService {

    final private PersonJdbcRepository repository;

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
