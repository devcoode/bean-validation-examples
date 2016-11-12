package example.ioc;

public class EmployeeService {

    public boolean isActive(EmployeeId id) {

        // In reality, you may want to do something like this here:
        // return entityManager.find(Employee.class, id.getValue()) != null;

        return id.getValue() == 1L;
    }
}
