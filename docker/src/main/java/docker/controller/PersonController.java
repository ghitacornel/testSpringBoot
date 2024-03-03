package docker.controller;

import docker.model.CreatePersonDto;
import docker.model.PersonDto;
import docker.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping
    public List<PersonDto> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "{id}")
    public PersonDto findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public PersonDto create(@RequestBody CreatePersonDto personDto) {
        return service.create(personDto);
    }

    @PutMapping
    public void update(@RequestBody PersonDto personDto) {
        service.save(personDto);
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
