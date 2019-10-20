package beans.services;

import beans.repository.UserRepository;
import beans.rest.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Set<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUser(Integer id) {
        return userRepository.getUser(id);
    }

    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.getUser(id);
        if (user == null) {
            throw new RuntimeException("user id not found");
        }
        userRepository.deleteUser(user);
    }
}
