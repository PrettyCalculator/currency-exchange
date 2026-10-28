package com.example.utils;


import com.example.exc.dao.DbConnectionException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;


public class ConnectionManager {
    private final HikariDataSource ds;

    public ConnectionManager(){
        HikariConfig config = new HikariConfig();



        config.setDriverClassName(PropertiesHandler.getProperty("DB_DRIVER"));
        config.setJdbcUrl(PropertiesHandler.getProperty("DB_URL"));
        config.setUsername(PropertiesHandler.getProperty("DB_USER"));
        config.setPassword(PropertiesHandler.getProperty("DB_PASSWORD"));

        ds = new HikariDataSource(config);
    }


    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new DbConnectionException("Не удалось подключиться к бд", e);
        }
    }

    public void closeDataSource() {
        ds.close();
    }
}
