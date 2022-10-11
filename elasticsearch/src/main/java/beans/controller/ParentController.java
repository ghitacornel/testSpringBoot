package beans.controller;


import beans.model.Parent;
import beans.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "parent")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService service;

    @GetMapping
    public List<Parent> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Parent findById(@PathVariable(name = "id") Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public void createSome(@RequestBody Parent parent) {
        service.createParent(parent);
    }

    @DeleteMapping
    public void deleteAll() {
        service.deleteAll();
    }

}
