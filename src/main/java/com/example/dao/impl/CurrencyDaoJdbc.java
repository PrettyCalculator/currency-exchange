package com.example.dao.impl;

import com.example.dao.CurrencyDao;
import com.example.exc.dao.DaoException;
import com.example.model.Currency;
import com.example.utils.ConnectionManager;
import com.example.utils.translator.SQLExceptionTranslator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrencyDaoJdbc implements CurrencyDao {
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
            throw SQLExceptionTranslator.translate("Не удалось получить все значения currencies", e);
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
            throw SQLExceptionTranslator.translate("Не удалось найти валюту по коду: " + code, e);
        }


        return Optional.empty();
    }

    @Override
    public Currency save(Currency currency) {
        String sql = "INSERT INTO currencies(code, fullname, sign) " +
                "VALUES (?, ?, ?) " +
                "RETURNING id, code, fullname, sign";

        try (Connection connection = connectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, currency.code());
            statement.setString(2, currency.fullName());
            statement.setString(3, currency.sign());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return map(resultSet);
                }
                throw new DaoException("Ошибка при сохранении валюты.");
            }

        } catch (SQLException e) {
            throw SQLExceptionTranslator.translate("Не удалось сохранить: " + currency, e);
        }
    }

    private Currency map(ResultSet resultSet) throws SQLException {
        return new Currency(
                resultSet.getInt("id"),
                resultSet.getString("code"),
                resultSet.getString("fullname"),
                resultSet.getString("sign"));
    }
}
