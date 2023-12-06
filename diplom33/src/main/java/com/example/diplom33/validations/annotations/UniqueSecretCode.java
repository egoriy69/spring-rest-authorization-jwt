package com.example.diplom33.validations.annotations;


import com.example.diplom33.validations.UniqueSecretCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueSecretCodeValidator.class)
public @interface UniqueSecretCode {
    public String message() default "секретный код не найден";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
