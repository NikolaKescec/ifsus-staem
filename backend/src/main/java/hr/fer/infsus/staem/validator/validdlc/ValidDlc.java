package hr.fer.infsus.staem.validator.validdlc;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDlcValidator.class)
@Documented
public @interface ValidDlc {

    String message() default "Invalid article!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
