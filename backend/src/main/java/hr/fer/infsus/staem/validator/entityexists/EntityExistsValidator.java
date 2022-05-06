package hr.fer.infsus.staem.validator.entityexists;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class EntityExistsValidator implements ConstraintValidator<EntityExists, Long> {

    private Class<JpaRepository<?, Long>> entityRepository;

    private final ApplicationContext applicationContext;

    @Override
    public void initialize(EntityExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.entityRepository = (Class<JpaRepository<?, Long>>) constraintAnnotation.repository();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return applicationContext.getBean(entityRepository).existsById(value);
    }

}
