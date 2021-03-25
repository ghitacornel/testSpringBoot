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

    @GetMapping(value = "/{id}")
    public Person findById(@PathVariable(name = "id") Integer id) {
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

    @GetMapping(value = "/transaction1")
    public void transaction1() {
        service.validate2TransactionsArePresent();
    }

    @GetMapping(value = "/transaction2")
    public void transaction2() {
        service.validate1TransactionIsPresent();
    }

    @GetMapping(value = "/transaction3")
    public void transaction3() {
        service.firstMethodTransactionalFailsAtTheEndSecondMethodTHISInvoked();
    }

    @GetMapping(value = "/transaction4")
    public void transaction4() {
        service.firstMethodTransactionalFailsAtTheEndSecondMethodSELFInvoked();
    }

    @PostMapping
    public void update(@RequestBody Person person) {
        service.save(person);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll() {
        service.deleteAll();
    }

}
