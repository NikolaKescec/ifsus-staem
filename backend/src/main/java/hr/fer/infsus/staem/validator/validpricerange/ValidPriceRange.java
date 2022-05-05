package hr.fer.infsus.staem.validator.validpricerange;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPriceRangeValidator.class)
@Documented
public @interface ValidPriceRange {

    String message() default "Price range is not valid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
