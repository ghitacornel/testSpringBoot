package beans.services;

import beans.rest.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    Set<User> users = new HashSet<>();

    @PostConstruct
    public void init() {
        users.add(new User(1, "ion", "parola"));
        users.add(new User(2, "gheorghe", "n-are parola"));
    }

    public Set<User> getUsers() {
        return users;
    }

    public User getUser(Integer id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User createUser(User user) {
        users.add(user);
        return user;
    }

    public void deleteUser(Integer id) {
        User user = getUser(id);
        if (user == null) {
            throw new RuntimeException("user id not found");
        }
        users.remove(user);
    }
}
