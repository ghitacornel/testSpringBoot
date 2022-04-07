package beans.jdbc.controller;

import beans.jdbc.model.PersonJdbc;
import beans.jdbc.service.PersonJdbcService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "user")
@RequiredArgsConstructor
public class PersonJdbcController {

    private final PersonJdbcService service;

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
