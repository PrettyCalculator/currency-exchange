package com.example.dao.impl;

import com.example.dao.CurrencyDAO;
import com.example.exc.DbCommunicationException;
import com.example.model.Currency;
import com.example.utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrencyDaoJdbc implements CurrencyDAO {
    private final ConnectionManager connectionManager;


    public CurrencyDaoJdbc(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<Currency> findAll() {
        String sql = "SELECT id , code, fullname, sign FROM currencies";
        List<Currency> result = new ArrayList<>();

        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                result.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DbCommunicationException("Не удалось получить список валют", e);
        }

        return result;
    }

    @Override
    public Optional<Currency> findByCode(String code) {
        String sql = "SELECT id , code, fullname, sign FROM currencies WHERE code = ?";


        try (Connection connection = connectionManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, code);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DbCommunicationException("Не удалось получить валюту по коду: " + code, e);
        }


        return Optional.empty();
    }

    private Currency map(ResultSet resultSet) throws SQLException {
        return new Currency(
                resultSet.getInt("id"),
                resultSet.getString("code"),
                resultSet.getString("fullname"),
                resultSet.getString("sign"));
    }
}
