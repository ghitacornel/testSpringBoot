package beans.repository;

import beans.rest.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Repository
public class UserRepository {

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

    public void deleteUser(User user) {
        users.remove(user);
    }

}
