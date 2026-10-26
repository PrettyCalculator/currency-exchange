package com.example.model;

import java.math.BigDecimal;

public record ConversionResult(Currency baseCurrency, Currency targetCurrency, BigDecimal rate,
                               BigDecimal amount,
                               BigDecimal convertedAmount) {}
