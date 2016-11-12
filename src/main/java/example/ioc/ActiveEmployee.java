package example.ioc;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Many parts were taken from https://github.com/xvik/guice-validator#custom-validator
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ActiveEmployeeValidator.class})
@Documented
public @interface ActiveEmployee {
    /* ideally there should be just localization key, but for simplicity just message */
    String message() default "This employee is not active";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
