package beans.rest.controller;

import beans.rest.model.User;
import beans.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAll();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public User findById(@RequestParam(name = "id") Integer id) {
        return userService.findById(id);
    }

    @PostMapping(value = "", consumes = "application/json")
    public void create(@RequestBody User user) {
        userService.create(user);
    }

    @DeleteMapping(value = "/{id}")
    public void removeById(@PathVariable("id") Integer id) {
        userService.removeById(id);
    }

}
