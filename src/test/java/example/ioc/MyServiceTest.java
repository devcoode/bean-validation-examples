package example.ioc;

import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JukitoRunner.class)
@UseModules(MyModule.class)
public class MyServiceTest {

    @Inject
    MyService sut;

    @Test
    public void hello() throws Exception {
        String name = "John";

        String actual = sut.hello(name);

        assertThat(actual, is("Hello, John"));
    }

    @Test(expected = ConstraintViolationException.class)
    public void validatesNull() throws Exception {
        String name = null;

        sut.hello(name);
    }

    @Test(expected = ConstraintViolationException.class)
    public void validatesTooLong() throws Exception {
        String name = "123456789";

        sut.hello(name);
    }

    @Test
    public void helloActiveEmployee() throws Exception {
        final EmployeeId activeEmployee = new EmployeeId(1);

        String actual = sut.hello(activeEmployee);

        assertThat(actual, is("Hello, you are an active employee: 1"));
    }

    @Test(expected = ConstraintViolationException.class)
    public void validatesNullEmployeeId() throws Exception {
        final EmployeeId nullId = null;

        sut.hello(nullId);
    }

    @Test(expected = ConstraintViolationException.class)
    public void helloInactiveEmployee() throws Exception {
        final EmployeeId inactiveEmployee = new EmployeeId(2);

        sut.hello(inactiveEmployee);
    }
}
