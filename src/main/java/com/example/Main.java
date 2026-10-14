package com.example;


import com.example.dao.CurrencyDAO;
import com.example.dao.impl.CurrencyDaoJdbc;
import com.example.model.Currency;
import com.example.utils.ConnectionManager;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        ConnectionManager conManager = new ConnectionManager();
        CurrencyDAO dao = new CurrencyDaoJdbc(conManager);
        Currency currency = dao.save(new Currency(null, "abc", "lit", "sign"));
        System.out.println(currency);
    }
}