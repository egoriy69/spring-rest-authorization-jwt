package com.example.diplom33.services;


import com.example.diplom33.dto.RegistrationUserDTO;
import com.example.diplom33.exceptions.NoSuchException;
import com.example.diplom33.models.Client;
import com.example.diplom33.models.Code;
import com.example.diplom33.models.Employee;
import com.example.diplom33.models.User;
import com.example.diplom33.repositories.ClientRepository;
import com.example.diplom33.repositories.CodeRepository;
import com.example.diplom33.repositories.EmployeeRepository;
import com.example.diplom33.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private final CodeRepository codeRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void createUser(RegistrationUserDTO registrationUser) {
        User user = new User();
        if (!registrationUser.getPassword().equals(registrationUser.getConfirmPassword())) {
            throw new NoSuchException("пароли не совпадают");
        }
        Optional<Code> code = codeRepository.findByCode(registrationUser.getSecretCode());
        user.setUsername(registrationUser.getUsername());
        user.setEmail(registrationUser.getEmail());
        user.setPassword(passwordEncoder.encode(registrationUser.getPassword()));
        user.setRoles(List.of(code.get().getRole()));

        if (Objects.equals(code.get().getRole().getName(), "ROLE_CLIENT")) {
            Client client = new Client();
            client.setUser(user);
            clientRepository.save(client);
        } else {
            Employee employee = new Employee();
            employee.setUser(user);
            employeeRepository.save(employee);
        }
        userRepository.save(user);
        codeRepository.delete(code.get());

    }
}
