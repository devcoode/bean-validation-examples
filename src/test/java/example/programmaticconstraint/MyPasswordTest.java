package example.programmaticconstraint;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.cfg.defs.NotNullDef;
import org.hibernate.validator.engine.HibernateConstraintViolation;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.annotation.ElementType;
import java.util.Set;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MyPasswordTest {

    private static Validator validator;
    private static Validator validator2;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeClass
    public static void setUpProgrammaticConstraintValidator() {
        HibernateValidatorConfiguration configuration = Validation
                .byProvider(HibernateValidator.class)
                .configure();
        ConstraintMapping constraintMapping = configuration.createConstraintMapping();
        constraintMapping
                .type(MyPassword.class)
                .constraint(new ValidPasswordDef()) // class level
                .property("value", ElementType.FIELD) // field level
                .constraint(new NotNullDef());
        validator2 = configuration.addMapping(constraintMapping)
                .buildValidatorFactory()
                .getValidator();
    }

    @Test
    public void noConstraint() throws Exception {
        final MyPassword sut = new MyPassword("bad password");

        final Set<ConstraintViolation<MyPassword>> violations = validator.validate(sut);

        assertThat(violations, is(empty()));
    }

    @Test
    public void correct() {
        final MyPassword sut = new MyPassword("correct");

        final Set<ConstraintViolation<MyPassword>> violations = validator2.validate(sut);

        assertThat(violations, is(empty()));
    }

    @Test
    public void nullPassword() {
        final MyPassword sut = new MyPassword(null);

        final Set<ConstraintViolation<MyPassword>> violations = validator2.validate(sut);

        assertThat(violations, contains(hasProperty("message", is("may not be null"))));
    }

    @Test
    public void badPassword() {
        final MyPassword sut = new MyPassword("bad password");

        final Set<ConstraintViolation<MyPassword>> violations = validator2.validate(sut);

        assertThat(violations, contains(hasProperty("message", is("Bad password"))));
        final String payload = (String) violations.iterator().next().unwrap(HibernateConstraintViolation.class).getDynamicPayload(String.class);
        assertThat(payload, is("you can return any typed instances to the caller dynamically through this way"));
    }
}
