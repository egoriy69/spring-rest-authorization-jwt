package com.example.diplom33.services;


import com.example.diplom33.models.Role;
import com.example.diplom33.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByName(String typeUser){

        return roleRepository.findByName(typeUser).get();
    }
}
