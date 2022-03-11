package beans.rest.validation.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@RequestMapping(value = "validateParameters")
@Validated// needed only if we want to validate manually the parameters sent to the service
public class ValidationControllerParameters {

    // validation rules placed on parameters
    // parameter validated by the REST Controller
    @GetMapping(value = "{id}")
    public int invokeDirect(@PathVariable("id") @Min(5) int x) {
        return x;
    }

}
