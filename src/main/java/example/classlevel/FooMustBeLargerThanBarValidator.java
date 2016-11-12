package example.classlevel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FooMustBeLargerThanBarValidator implements ConstraintValidator<FooMustBeLargerThanBar, SomeNumbers> {

    @Override
    public void initialize(final FooMustBeLargerThanBar constraintAnnotation) {
    }

    @Override
    public boolean isValid(final SomeNumbers value, final ConstraintValidatorContext context) {
        return value.getFoo() == null || value.getBar() == null || value.getFoo().compareTo(value.getBar()) > 0;
    }
}
