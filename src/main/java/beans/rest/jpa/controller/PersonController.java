package beans.rest.jpa.controller;

import beans.rest.jpa.model.Person;
import beans.rest.jpa.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    PersonService service;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Person> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "")
    public Person findById(@RequestParam(name = "id") Integer id) {
        return service.findById(id);
    }

    @GetMapping(value = "/{password}")
    public List<Person> findByPassword(@PathVariable String password) {
        return service.findByPassword(password);
    }

    @PutMapping(value = "")
    public void create(@RequestBody Person person) {
        service.save(person);
    }

    @PostMapping(value = "")
    public void update(@RequestBody Person person) {
        service.save(person);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }

}
