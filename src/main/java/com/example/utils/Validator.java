package com.example.utils;

import com.example.exc.service.ValidationException;

import java.math.BigDecimal;

public final class Validator {


    private final static int CODE_LENGTH = 3;
    private final static int MAX_FULLNAME_LENGTH = 50;
    private final static int MIN_FULLNAME_LENGTH = 1;
    private final static int MAX_SIGN_LENGTH = 5;
    private final static int MIN_SIGN_LENGTH = 1;

    private Validator() {}

    public static void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Неверная сумма для перевода в другую валюту.");
        }
    }

    public static void validateCode(String code) {
        if (code == null || code.isBlank()) {
            throw new ValidationException("Пустой код валюты.");
        }

        if (!code.matches("[A-Za-z]+")) {
            throw new ValidationException("В коде использованы недопустимые символы");
        }

        if (checkStringLength(code, CODE_LENGTH)) {
            throw new ValidationException("Длина кода != 3");
        }
    }

    public static void validateRate(BigDecimal rate) {
        if (rate == null || rate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Неверный rate.");
        }
    }

    public static void validateSign(String sign) {
        if (sign == null || sign.isBlank()) {
            throw new ValidationException("Пустой знак валюты");
        }

        if (checkStringLength(sign, MIN_SIGN_LENGTH, MAX_SIGN_LENGTH)) {
            throw new ValidationException("Некорректная длина знака");
        }
    }

    public static void validateFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new ValidationException("Пустое имя валюты");
        }

        if (checkStringLength(fullName, MIN_FULLNAME_LENGTH, MAX_FULLNAME_LENGTH)) {
            throw new ValidationException("Некорректная длина имени валюты.");
        }

    }

    private static boolean checkStringLength(String s, int min, int max) {
        return s.length() < min || s.length() > max;
    }

    private static boolean checkStringLength(String s, int length) {
        return !(s.length() == length);
    }

}
