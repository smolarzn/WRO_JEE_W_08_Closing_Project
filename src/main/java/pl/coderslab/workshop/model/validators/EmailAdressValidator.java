package pl.coderslab.workshop.model.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailAdressValidator implements ConstraintValidator<EmailAdress, String> {

    private final Pattern pattern = Pattern.compile("[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}");

    public void initialize(EmailAdress constraint) {
    }

    public boolean isValid(String obj, ConstraintValidatorContext context) {
        Matcher matcher = pattern.matcher(obj);
        return matcher.matches();
    }
}
