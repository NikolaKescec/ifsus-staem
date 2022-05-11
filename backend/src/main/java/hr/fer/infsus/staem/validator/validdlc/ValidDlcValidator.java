package hr.fer.infsus.staem.validator.validdlc;

import hr.fer.infsus.staem.entity.ArticleType;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ValidDlcValidator implements ConstraintValidator<ValidDlc, ValidDlcFields> {

    @Override
    public boolean isValid(ValidDlcFields value, ConstraintValidatorContext context) {
        if (value.getArticleType() == ArticleType.DLC && (value.getBaseArticleId() == null)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("DLCs have to have a base article.")
                .addConstraintViolation();

            return false;
        }
        if (value.getArticleType() != ArticleType.DLC && (value.getBaseArticleId() != null)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Non game articles can not have dlcs.")
                .addConstraintViolation();

            return false;
        }

        return true;
    }

}