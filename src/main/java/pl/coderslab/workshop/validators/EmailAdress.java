package pl.coderslab.workshop.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailAdressValidator.class)
public @interface EmailAdress {
    String message() default "{email.adress.error.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
