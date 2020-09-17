package pl.coderslab.workshop.model.aircraftProperties;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {


    @Override
    public void initialize(Password constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("[[a-z]+[A-Z]+[0-9]+[_!.]+]{5,}");

    }

}
