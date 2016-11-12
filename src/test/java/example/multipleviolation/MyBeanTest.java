package example.multipleviolation;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MyBeanTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void noViolation() {
        final Set<Character> mandatoryValues = new HashSet<>();
        Collections.addAll(mandatoryValues, 'f', 'o');
        final MyBean sut = new MyBean(mandatoryValues, "foo");

        final Set<ConstraintViolation<MyBean>> violations = validator.validate(sut);

        assertThat(violations, is(empty()));
    }

    @Test
    public void twoViolation() {
        final Set<Character> mandatoryValues = new HashSet<>();
        Collections.addAll(mandatoryValues, 'f', 'y', 'z');
        final MyBean sut = new MyBean(mandatoryValues, "foo");

        final Set<ConstraintViolation<MyBean>> violations = validator.validate(sut);

        assertThat(violations, containsInAnyOrder(
                hasProperty("message", is("missing mandatory char: y")),
                hasProperty("message", is("missing mandatory char: z"))));
    }
}
