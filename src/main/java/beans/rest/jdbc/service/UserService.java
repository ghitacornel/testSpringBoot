package beans.rest.jdbc.service;

import beans.rest.jdbc.model.PersonJDBC;
import beans.rest.jdbc.repository.UserJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserJDBCRepository repository;

    public List<PersonJDBC> findAll() {
        return repository.findAll();
    }

    public PersonJDBC findById(Integer id) {
        return repository.findById(id);
    }

    public void create(PersonJDBC personJDBC) {
        repository.create(personJDBC);
    }

    public void removeById(Integer id) {
        repository.removeById(id);
    }

    public void update(PersonJDBC personJDBC) {
        repository.update(personJDBC);
    }
}
