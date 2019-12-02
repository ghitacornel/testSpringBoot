package beans.rest.jdbc.controller;

import beans.rest.jdbc.model.User;
import beans.rest.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService service;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "")
    public User findById(@RequestParam(name = "id") Integer id) {
        return service.findById(id);
    }

    @PutMapping(value = "")
    public void create(@RequestBody User user) {
        service.create(user);
    }

    @PostMapping(value = "")
    public void update(@RequestBody User user) {
        service.update(user);
    }

    @DeleteMapping(value = "/{id}")
    public void removeById(@PathVariable("id") Integer id) {
        service.removeById(id);
    }

}
