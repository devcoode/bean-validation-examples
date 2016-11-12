package example.ioc;

public class EmployeeId {

    private final long value;

    public EmployeeId(final long value) {
        this.value = value;
    }

    public EmployeeId(final String value) {
        this(Long.parseLong(value));
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }
}
