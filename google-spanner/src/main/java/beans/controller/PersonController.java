package beans.controller;

import beans.model.Person;
import beans.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping
    public List<Person> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "{id}")
    public Person findById(@PathVariable("id") Integer id) {
        return service.findById(id);
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
