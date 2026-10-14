package com.example;


import com.example.dao.CurrencyDAO;
import com.example.dao.impl.CurrencyDaoJdbc;
import com.example.utils.ConnectionManager;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        ConnectionManager conManager = new ConnectionManager();
        CurrencyDAO dao = new CurrencyDaoJdbc(conManager);
        System.out.println(dao.findAll());
        System.out.println(dao.findByCode("USD"));
        System.out.println(dao.findByCode("XXX"));
    }
}