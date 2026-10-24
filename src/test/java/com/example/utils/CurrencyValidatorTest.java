package com.example.utils;



import static org.junit.jupiter.api.Assertions.*;

import com.example.exc.service.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CurrencyValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "   "})
    public void emptyCodeDoesNotPassValidation(String string) {
        assertThrows(ValidationException.class, () -> Validator.validateCode(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "   "})
    public void emptyFullNameDoesNotPassValidation(String string) {
        assertThrows(ValidationException.class, () -> Validator.validateFullName(string));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "   "})
    public void emptySignDoesNotPassValidation(String string) {
        assertThrows(ValidationException.class, () -> Validator.validateSign(string));
    }

    @Test
    public void nullValuesDoesNotPassValidation() {
        assertThrows(ValidationException.class, () -> {
            Validator.validateFullName(null);
            Validator.validateCode(null);
            Validator.validateSign(null);}
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "12", "1234", ""})
    public void codeWithInvalidLengthDoesNotPassValidation(String code) {
        assertThrows(ValidationException.class, () -> Validator.validateCode(code));
    }

    @Test
    public void fullNameWithInvalidLengthDoesNotPassValidation() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 51; ++i) {
            sb.append(1);
        }

        assertThrows(ValidationException.class,
                () -> Validator.validateFullName(sb.toString()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"12", "", "1234"})
    public void signWithInvalidLengthDoesNotPassValidation(String code) {
        assertThrows(ValidationException.class,
                () -> Validator.validateSign(code));
    }

    @ParameterizedTest
    @ValueSource(strings = {"...", ".@!", "ала", "///", ",,,", "212", "123", "ААА"})
    public void codeWithInvalidCharactersDoesNotPassValidation(String code) {
        assertThrows(ValidationException.class,
                () -> Validator.validateCode(code));
    }



}
