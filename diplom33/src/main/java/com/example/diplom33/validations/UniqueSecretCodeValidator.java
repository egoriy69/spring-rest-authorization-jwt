package com.example.diplom33.validations;

import com.example.diplom33.models.Code;

import com.example.diplom33.repositories.CodeRepository;
import com.example.diplom33.validations.annotations.UniqueSecretCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UniqueSecretCodeValidator implements ConstraintValidator<UniqueSecretCode, String> {
    private final CodeRepository codeRepository;
    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Code> c = codeRepository.findByCode(code);
        if(c.isEmpty()){
            return false;
        }else{
//            codeRepository.delete(c.get());
            return true;
        }
    }
}
