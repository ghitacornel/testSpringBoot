package beans.validations.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomValidationRuleConstraintValidator implements ConstraintValidator<CustomValidationRuleForParameter, String> {

    @Override
    public void initialize(CustomValidationRuleForParameter constraintAnnotation) {
        // do nothing
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return value.equals("UP") || value.equals("DOWN");
    }
}
