package com.example.utils;

public final class Normalizer {

    private Normalizer() {}

    public static String code(String code) {
        return code.trim().toUpperCase();
    }

    public static String fullName(String fullName) {
        return fullName.trim();
    }

    public static String sign(String sign) {
        return sign.trim();
    }
}
