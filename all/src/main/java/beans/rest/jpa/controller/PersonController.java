package beans.rest.jpa.controller;

import beans.rest.jpa.model.Person;
import beans.rest.jpa.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Person> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "{id}")
    public Person findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @GetMapping
    public List<Person> findByPassword(@RequestParam(name = "pass") String password) {
        return service.findByPassword(password);
    }

    @PutMapping
    public void create(@RequestBody Person person) {
        service.save(person);
    }

    @PostMapping
    public void update(@RequestBody Person person) {
        service.save(person);
    }

    @DeleteMapping(value = "{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        service.deleteAll();
    }

}
