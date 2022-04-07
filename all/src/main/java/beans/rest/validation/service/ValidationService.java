package beans.rest.validation.service;

import beans.rest.validation.model.ValidationModel;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated// needed in order to tell Spring to validate this service
public class ValidationService {

    public ValidationModel invoke(@Valid ValidationModel model) {
        return model;
    }

}
