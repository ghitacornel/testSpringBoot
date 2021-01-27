package beans.rest.jpa.controller;

import beans.rest.jpa.model.Parent;
import beans.rest.jpa.model.Person;
import beans.rest.jpa.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/parent")
public class ParentController {

    @Autowired
    ParentService service;

    @GetMapping(value = "/all")
    public List<Parent> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Parent findById(@PathVariable(name = "id") Integer id) {
        return service.findById(id);
    }

    @PutMapping(value = "")
    public void createSome() {
        service.createSome();
    }

    @DeleteMapping(value = "")
    public void deleteAll() {
        service.deleteAll();
    }

}
