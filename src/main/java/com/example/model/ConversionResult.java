package com.example.model;

import java.math.BigDecimal;

public record ConversionResult(Currency base, Currency target, BigDecimal rate,
                               BigDecimal amount,
                               BigDecimal convertedAmount) {}
