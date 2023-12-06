package com.example.diplom33.validations.annotations;


import com.example.diplom33.validations.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    public String message() default "данный email уже зарегистрирован";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
