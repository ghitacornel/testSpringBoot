package beans.services;

import beans.rest.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        users.add(new User(1, "ion", "parola"));
        users.add(new User(2, "gheorghe", "n-are parola"));
    }

    public List<User> getUsers() {
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
}
