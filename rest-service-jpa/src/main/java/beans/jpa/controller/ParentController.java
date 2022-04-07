package beans.jpa.controller;

import beans.jpa.model.Parent;
import beans.jpa.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "parent")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService service;

    @GetMapping(value = "all")
    public List<Parent> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "{id}")
    public Parent findById(@PathVariable(name = "id") Integer id) {
        return service.findById(id);
    }

    @PutMapping
    public void createSome() {
        service.createSome();
    }

    @DeleteMapping
    public void deleteAll() {
        service.deleteAll();
    }

}
