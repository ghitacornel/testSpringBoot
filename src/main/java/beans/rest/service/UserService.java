package beans.rest.service;

import beans.rest.repository.UserJDBCRepository;
import beans.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserJDBCRepository repository;

    public List<User> getAll() {
        return repository.getAll();
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
}
