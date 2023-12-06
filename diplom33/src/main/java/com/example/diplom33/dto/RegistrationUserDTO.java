package com.example.diplom33.dto;



import com.example.diplom33.validations.annotations.UniqueEmail;
import com.example.diplom33.validations.annotations.UniqueSecretCode;
import com.example.diplom33.validations.annotations.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class RegistrationUserDTO {

    @UniqueUsername
    @NotBlank(message = "поле не может быть пустым")
    private String username;

    @UniqueEmail(message = "этот email уже занят")
    @Email(message = "не валидный email")
    private String email;

    @NotNull
    @Size(min = 6, message = "пароль должен содержать минимум 6 символов")
    private String password;

    private String confirmPassword;
    @UniqueSecretCode
    private String secretCode;
}
