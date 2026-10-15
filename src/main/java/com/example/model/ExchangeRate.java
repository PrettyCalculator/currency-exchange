package com.example.model;

import java.math.BigDecimal;

public record ExchangeRate(Integer id, Currency baseCurrency, Currency targetCurrency, BigDecimal rate) {
}
