package example.classlevel;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FooMustBeLargerThanBarValidator.class})
@Documented
public @interface FooMustBeLargerThanBar {
    /* ideally there should be just localization key, but for simplicity just message */
    String message() default "Foo must be larger than bar";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
