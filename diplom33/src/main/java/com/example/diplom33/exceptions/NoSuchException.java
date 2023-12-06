package com.example.diplom33.exceptions;

import lombok.Data;


public class NoSuchException extends RuntimeException{
    public NoSuchException(String message) {
        super(message);
    }
}

