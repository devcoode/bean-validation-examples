package example.classlevel;


import javax.validation.constraints.NotNull;

@FooMustBeLargerThanBar
public class SomeNumbers {

    @NotNull
    private Integer foo;
    @NotNull
    private Integer bar;


    public Integer getFoo() {
        return foo;
    }

    public void setFoo(final Integer foo) {
        this.foo = foo;
    }

    public Integer getBar() {
        return bar;
    }

    public void setBar(final Integer bar) {
        this.bar = bar;
    }
}
