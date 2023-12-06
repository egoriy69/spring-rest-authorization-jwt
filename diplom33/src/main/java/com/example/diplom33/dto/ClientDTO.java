package com.example.diplom33.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ClientDTO {

    private String firstname;

    private String lastname;

    private String patronymic;

    private String email;

    private Date birth;

    private String phone;


}
