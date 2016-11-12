package example.classlevel;

import example.classlevel.SomeNumbers;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SomeNumbersTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void bothNumbersNull() {
        final SomeNumbers sut = new SomeNumbers();

        final Set<ConstraintViolation<SomeNumbers>> violations = validator.validate(sut);

        assertThat(violations.size(), is(2));
    }

    @Test
    public void bothSameValue() throws Exception {
        final SomeNumbers sut = new SomeNumbers();
        sut.setFoo(1);
        sut.setBar(1);

        final Set<ConstraintViolation<SomeNumbers>> violations = validator.validate(sut);

        assertThat(violations.size(), is(1));
        assertThat(violations.iterator().next().getMessage(), is("Foo must be larger than bar"));
    }

    @Test
    public void FooIsLargerThanBar() throws Exception {
        final SomeNumbers sut = new SomeNumbers();
        sut.setFoo(2);
        sut.setBar(1);

        final Set<ConstraintViolation<SomeNumbers>> violations = validator.validate(sut);

        assertThat(violations, is(empty()));
    }
}
