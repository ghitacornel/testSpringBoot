package beans.rest.jdbc.controller;

import beans.rest.jdbc.model.PersonJDBC;
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
    public List<PersonJDBC> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "")
    public PersonJDBC findById(@RequestParam(name = "id") Integer id) {
        return service.findById(id);
    }

    @PutMapping(value = "")
    public void create(@RequestBody PersonJDBC personJDBC) {
        service.create(personJDBC);
    }

    @PostMapping(value = "")
    public void update(@RequestBody PersonJDBC personJDBC) {
        service.update(personJDBC);
    }

    @DeleteMapping(value = "/{id}")
    public void removeById(@PathVariable("id") Integer id) {
        service.removeById(id);
    }

}
