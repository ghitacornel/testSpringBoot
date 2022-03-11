package beans.rest.validation.controller;

import beans.rest.validation.model.Model;
import beans.rest.validation.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "validate")
@RequiredArgsConstructor
public class ValidationController {

    private final ValidationService service;

    @PutMapping(value = "rest")
    public Model invokeRest(@Valid @RequestBody Model model) {
        return model;
    }

    @PutMapping(value = "service")
    public Model invokeService(@RequestBody Model model) {
        return service.invoke(model);
    }

}
