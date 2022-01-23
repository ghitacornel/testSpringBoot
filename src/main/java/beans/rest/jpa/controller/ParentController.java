package beans.rest.jpa.controller;

import beans.rest.jpa.model.Parent;
import beans.rest.jpa.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/parent")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService service;

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
