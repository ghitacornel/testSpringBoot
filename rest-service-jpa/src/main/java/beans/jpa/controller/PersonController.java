package beans.jpa.controller;

import beans.jpa.model.Person;
import beans.jpa.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
@RequiredArgsConstructor
class PersonController {

    private final PersonService service;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    List<Person> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    Person findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @GetMapping
    List<Person> findByPassword(@RequestParam("pass") String password) {
        return service.findByPassword(password);
    }

    @PutMapping
    void create(@RequestBody Person person) {
        service.save(person);
    }

    @PostMapping
    void update(@RequestBody Person person) {
        service.save(person);
    }

    @DeleteMapping(value = "{id}")
    void deleteById(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }

    @DeleteMapping
    void deleteAll() {
        service.deleteAll();
    }

}
