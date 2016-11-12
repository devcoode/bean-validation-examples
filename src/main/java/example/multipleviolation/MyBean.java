package example.multipleviolation;

import javax.validation.constraints.NotNull;
import java.util.Set;

@MandatoryCharsUsed
public class MyBean {

    @NotNull
    private final Set<Character> mandatoryChars;
    @NotNull
    private final String text;

    public MyBean(final Set<Character> mandatoryChars, final String text) {
        this.mandatoryChars = mandatoryChars;
        this.text = text;
    }

    public Set<Character> getMandatoryChars() {
        return mandatoryChars;
    }

    public String getText() {
        return text;
    }
}
