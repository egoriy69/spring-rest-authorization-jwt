package com.example.diplom33.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDTO {

    private String username;

    private String email;
}
