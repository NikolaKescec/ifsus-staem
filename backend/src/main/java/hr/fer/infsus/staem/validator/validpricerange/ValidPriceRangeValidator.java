package hr.fer.infsus.staem.validator.validpricerange;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ValidPriceRangeValidator implements ConstraintValidator<ValidPriceRange, ValidPriceRangeFields> {

    @Override
    public boolean isValid(ValidPriceRangeFields value, ConstraintValidatorContext context) {
        if (value.getMinPrice() == null && value.getMaxPrice() == null) {
            return true;
        }

        if ((value.getMinPrice() != null && value.getMaxPrice() == null)
            || (value.getMinPrice() == null && value.getMaxPrice() != null)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Min and max price must be set together")
                .addConstraintViolation();

            return false;
        }

        if (value.getMinPrice() > value.getMaxPrice()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Min price can not be greater then max price")
                .addPropertyNode("minPrice")
                .addConstraintViolation();

            return false;
        }

        if (value.getMaxPrice() < value.getMinPrice()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Max price can not be lower then min price")
                .addPropertyNode("maxPrice")
                .addConstraintViolation();

            return false;
        }

        return true;
    }

}