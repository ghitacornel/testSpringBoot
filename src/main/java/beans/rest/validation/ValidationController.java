package beans.rest.validation;

import beans.rest.jpa.service.PersonService;
import beans.rest.validation.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/validate")
public class ValidationController {

    @Autowired
    PersonService service;

    @PutMapping(value = "/rest")
    public Model invoke(@Valid @RequestBody Model model) {
        return model;
    }

}
