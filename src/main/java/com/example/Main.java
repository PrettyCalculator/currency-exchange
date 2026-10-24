package com.example;


import com.example.dao.CurrencyDao;
import com.example.dao.ExchangeRateDao;
import com.example.dao.impl.CurrencyDaoJdbc;
import com.example.dao.impl.ExchangeRateDaoJdbc;
import com.example.model.Currency;
import com.example.model.ExchangeRate;
import com.example.service.ConversionService;
import com.example.utils.ConnectionManager;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        ConnectionManager conManager = new ConnectionManager();
        ExchangeRateDao exchangeRateDao = new ExchangeRateDaoJdbc(conManager);
        CurrencyDao currencyDAO = new CurrencyDaoJdbc(conManager);

        Currency base = currencyDAO.findByCode("USD").get();
        Currency target = currencyDAO.findByCode("RUB").get();
        ConversionService service = new ConversionService(exchangeRateDao, currencyDAO);
        var result = service.convert("RUB", "EUR", BigDecimal.valueOf(100));
        System.out.println(result.rate());
        System.out.println(result.amount());

    }
}