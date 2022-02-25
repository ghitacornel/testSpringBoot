package beans.rest.jdbc.controller;

import beans.aop.LogExecution;
import beans.aop.LogExecutionTime;
import beans.rest.jdbc.model.PersonJdbc;
import beans.rest.jdbc.service.PersonJdbcService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@LogExecution
@RestController
@RequestMapping(value = "user")
public class PersonJdbcController {

    final PersonJdbcService service;

    public PersonJdbcController(PersonJdbcService service) {
        this.service = service;
    }

    @LogExecutionTime
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<PersonJdbc> findAll() {
        return service.findAll();
    }

    @GetMapping
    public PersonJdbc findById(@RequestParam(name = "id") Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public void create(@RequestBody PersonJdbc personJDBC) {
        service.create(personJDBC);
    }

    @PostMapping
    public void update(@RequestBody PersonJdbc personJDBC) {
        service.update(personJDBC);
    }

    @DeleteMapping(value = "{id}")
    public void removeById(@PathVariable("id") Integer id) {
        service.removeById(id);
    }

}
