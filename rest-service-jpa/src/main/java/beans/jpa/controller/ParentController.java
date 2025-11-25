package beans.jpa.controller;

import beans.jpa.model.Parent;
import beans.jpa.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parent")
@RequiredArgsConstructor
class ParentController {

    private final ParentService service;

    @GetMapping("all")
    List<Parent> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    Parent findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @PutMapping
    void createSome() {
        service.createSome();
    }

    @DeleteMapping
    void deleteAll() {
        service.deleteAll();
    }

}
