package beans.rest;

import beans.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Set<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public User getUser(@RequestParam(name = "id") Integer id) {
        return userService.getUser(id);
    }

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
    }

}
