package docker.controller;

import docker.model.CreatePersonDto;
import docker.model.PersonDto;
import docker.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "person")
@RequiredArgsConstructor
@Validated
public class PersonController {

    private final PersonService service;

    @GetMapping
    public List<PersonDto> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "{id}")
    public PersonDto findById(@Valid @NotNull @PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public PersonDto create(@Valid @RequestBody CreatePersonDto personDto) {
        return service.create(personDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody PersonDto personDto) {
        service.update(personDto);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Valid @NotNull @PathVariable("id") Integer id) {
        service.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        service.deleteAll();
    }

}
