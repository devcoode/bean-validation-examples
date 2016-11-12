package example.ioc;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;

@ValidateOnExecution
public class MyService {

    public String hello(@NotNull @Length(max = 8) String name) {
        return "Hello, " + name;
    }

    public String hello(@NotNull @ActiveEmployee EmployeeId id) {
        return "Hello, you are an active employee: " + id;
    }
}
