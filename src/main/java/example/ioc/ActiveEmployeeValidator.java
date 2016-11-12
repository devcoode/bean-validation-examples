package example.ioc;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// Many parts were taken from https://github.com/xvik/guice-validator#custom-validator
public class ActiveEmployeeValidator implements ConstraintValidator<ActiveEmployee, EmployeeId>{

    @Inject
    private EmployeeService employeeService;

    @Override
    public void initialize(final ActiveEmployee constraintAnnotation) {
        /* if annotation contains addition parameter it must be parsed here.. skipping for simplicity.
          NOTE: in such simple case we can make validator singleton, because of no internal state */
    }

    @Override
    public boolean isValid(final EmployeeId value, final ConstraintValidatorContext context) {
        /* common convention is to treat null values as valid and explicitly check them with @NotNull */
        return value == null || employeeService.isActive(value);
    }
}
