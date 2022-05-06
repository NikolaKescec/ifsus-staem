package hr.fer.infsus.staem.validator.entityexists;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EntityExistsValidator.class)
@Documented
public @interface EntityExists {

    String message() default "Entity does not exist";

    Class<?> repository();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
