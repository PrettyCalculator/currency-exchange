package com.example.servlet;

public enum Attributes {
    CONVERSION_SERVICE("conversionService"),
    CURRENCY_SERVICE("currencyService"),
    EXCHANGE_RATE_SERVICE("exchangeRateService"),
    OBJECT_MAPPER("objectMapper");


    private String value;

    Attributes(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
