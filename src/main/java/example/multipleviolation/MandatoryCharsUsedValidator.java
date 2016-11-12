package example.multipleviolation;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MandatoryCharsUsedValidator implements ConstraintValidator<MandatoryCharsUsed, MyBean> {

    @Override
    public void initialize(final MandatoryCharsUsed constraintAnnotation) {
    }

    @Override
    public boolean isValid(final MyBean value, final ConstraintValidatorContext context) {
        if (value == null || value.getMandatoryChars() == null || value.getText() == null) {
            return true;
        }

        // https://docs.oracle.com/javaee/7/api/javax/validation/ConstraintValidatorContext.html#buildConstraintViolationWithTemplate-java.lang.String-

        boolean valid = true;

        for (final Character character : value.getMandatoryChars()) {
            if (value.getText().contains(character.toString())) {
                continue;
            }
            context.buildConstraintViolationWithTemplate("missing mandatory char: " + character).addConstraintViolation();
            context.disableDefaultConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
