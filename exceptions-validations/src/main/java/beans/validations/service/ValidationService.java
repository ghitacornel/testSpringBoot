package beans.validations.service;

import beans.validations.model.ValidationModel;
import beans.validations.repository.ValidationModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated// needed in order to tell Spring to validate this service
@RequiredArgsConstructor
public class ValidationService {

    private final ValidationModelRepository repository;

    public ValidationModel invoke(@Valid ValidationModel model) {
        return model;
    }

    public ValidationModel invokeRepository(ValidationModel model) {
        repository.save(model);
        return model;
    }
}
