package com.example.utils;


import java.sql.*;


public class ConnectionManager {
    private final String url;
    private final String user;
    private final String password;

    public ConnectionManager() {
        url = PropertiesHandler.getProperty("db.url");
        user = PropertiesHandler.getProperty("db.user");
        password = PropertiesHandler.getProperty("db.password");
    }



    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new DbCommunicationException("Не удалось подключиться к бд", e);
        }
    }



}
