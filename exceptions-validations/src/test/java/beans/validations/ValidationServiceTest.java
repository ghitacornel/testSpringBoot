package beans.validations;

import beans.AbstractTestSpringBootContext;
import beans.validations.service.ValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

public class ValidationServiceTest extends AbstractTestSpringBootContext {

    @Autowired
    ValidationService service;

    @Test
    public void returnBadData() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.returnBadData());
    }

}
