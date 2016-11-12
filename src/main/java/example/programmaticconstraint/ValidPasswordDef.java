package example.programmaticconstraint;

import org.hibernate.validator.cfg.ConstraintDef;

public class ValidPasswordDef extends ConstraintDef<ValidPasswordDef, ValidPassword> {

    protected ValidPasswordDef() {
        super(ValidPassword.class);
    }
}
