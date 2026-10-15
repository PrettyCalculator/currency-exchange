package com.example;


import com.example.dao.CurrencyDAO;
import com.example.dao.ExchangeRateDao;
import com.example.dao.impl.CurrencyDaoJdbc;
import com.example.dao.impl.ExchangeRateDaoJdbc;
import com.example.model.Currency;
import com.example.model.ExchangeRate;
import com.example.utils.ConnectionManager;

import java.math.BigDecimal;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        ConnectionManager conManager = new ConnectionManager();
        ExchangeRateDao exchangeRateDao = new ExchangeRateDaoJdbc(conManager);
        CurrencyDAO currencyDAO = new CurrencyDaoJdbc(conManager);

        Currency base = currencyDAO.findByCode("USD").get();
        Currency target = currencyDAO.findByCode("RUB").get();
        ExchangeRate exchangeRate = new ExchangeRate(null, base, target, BigDecimal.valueOf(0.1));

        System.out.println(exchangeRateDao.update(exchangeRate));

    }
}