package beans.rest.validation.controller;

import beans.rest.validation.model.ValidationModel;
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

    // model validated by the REST Controller
    @PutMapping(value = "rest")
    public ValidationModel invokeRest(@Valid @RequestBody ValidationModel model) {
        return model;
    }

    // model validated in the Service layer
    @PutMapping(value = "service")
    public ValidationModel invokeService(@RequestBody ValidationModel model) {
        return service.invoke(model);
    }

}
