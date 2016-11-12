package example.ioc;

import com.google.inject.AbstractModule;
import ru.vyarus.guice.validator.ValidationModule;

public class MyModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ValidationModule());
    }
}
