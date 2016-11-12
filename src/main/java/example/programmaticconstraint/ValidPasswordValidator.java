package example.programmaticconstraint;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, MyPassword> {

    @Override
    public void initialize(final ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(final MyPassword value, final ConstraintValidatorContext context) {
        if (value == null || value.getValue() == null || Objects.equals(value.getValue(), "correct")) {
            return true;
        }

        HibernateConstraintValidatorContext hibernateContext = context.unwrap(HibernateConstraintValidatorContext.class);

        hibernateContext.withDynamicPayload("you can return any typed instances to the caller dynamically through this way");

        return false;
    }
}
