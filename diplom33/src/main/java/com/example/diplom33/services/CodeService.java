package com.example.diplom33.services;


import com.example.diplom33.models.Code;
import com.example.diplom33.repositories.CodeRepository;
import com.example.diplom33.util.CreateSecretCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CodeService {


    private final CodeRepository codeRepository;
    private final RoleService roleService;


    @Transactional
    public Code createCode(String typeUser){
        Code code = new Code();
        code.setCode(CreateSecretCode.generateRandomCode());
        code.setRole(roleService.findByName(typeUser));
        codeRepository.save(code);
        return code;
    }
}
