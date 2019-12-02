package beans.rest.jdbc.service;

import beans.rest.jdbc.repository.UserJDBCRepository;
import beans.rest.jdbc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserJDBCRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Integer id) {
        return repository.findById(id);
    }

    public void create(User user) {
        repository.create(user);
    }

    public void removeById(Integer id) {
        repository.removeById(id);
    }

    public void update(User user) {
        repository.update(user);
    }
}
