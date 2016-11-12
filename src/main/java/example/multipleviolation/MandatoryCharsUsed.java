package example.multipleviolation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MandatoryCharsUsedValidator.class})
@Documented
public @interface MandatoryCharsUsed {
    /* ideally there should be just localization key, but for simplicity just message */
    String message() default "Any of mandatory values are missing";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
