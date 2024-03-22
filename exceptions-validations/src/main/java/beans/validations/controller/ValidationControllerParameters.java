package beans.validations.controller;

import beans.validations.custom.CustomValidationRuleForParameter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

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

    @GetMapping(value = "{parameter1}/partialPath/{parameter2}")
    public String invokeDirect(@PathVariable("parameter1") @NotBlank String parameter1, @PathVariable("parameter2") @NotBlank String parameter2) {
        return parameter1 + parameter2;
    }

    @GetMapping("customParameterValue/{data}")
    public String customParameterValue(@Valid @CustomValidationRuleForParameter @PathVariable("data") String data) {
        return data + " is OK";
    }
}
