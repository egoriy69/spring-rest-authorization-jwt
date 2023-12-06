package com.example.diplom33.util;

import org.apache.commons.lang3.RandomStringUtils;

public class CreateSecretCode {
    private static final int CODE_LENGTH = 20;

    public static String generateRandomCode() {
        return RandomStringUtils.randomAlphanumeric(CODE_LENGTH);
    }


}
