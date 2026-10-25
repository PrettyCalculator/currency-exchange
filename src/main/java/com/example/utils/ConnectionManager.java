package com.example.utils;


import com.example.exc.dao.DbConnectionException;

import java.sql.*;


public class ConnectionManager {
    private final String url;
    private final String user;
    private final String password;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("PostgreSQL driver not found", e);
        }
    }

    public ConnectionManager(){
        url = PropertiesHandler.getProperty("db.url");
        user = PropertiesHandler.getProperty("db.user");
        password = PropertiesHandler.getProperty("db.password");
    }



    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new DbConnectionException("Не удалось подключиться к бд", e);
        }
    }



}
