package beans.rest.validation.service;

import beans.rest.validation.model.Model;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class ValidationService {

    public Model invoke(@Valid Model model) {
        return model;
    }

}
