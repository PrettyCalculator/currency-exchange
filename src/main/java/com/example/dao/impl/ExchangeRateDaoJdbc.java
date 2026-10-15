package com.example.dao.impl;

import com.example.dao.ExchangeRateDao;
import com.example.exc.DaoException;
import com.example.model.ExchangeRate;
import com.example.utils.ConnectionManager;
import com.example.utils.Translator;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import com.example.model.Currency;
import java.util.List;
import java.util.Optional;

public class ExchangeRateDaoJdbc implements ExchangeRateDao {
    private final ConnectionManager manager;

    public ExchangeRateDaoJdbc(ConnectionManager manager) {
        this.manager = manager;
    }

    @Override
    public List<ExchangeRate> findAll() {
        String sql = """
                SELECT rates.id AS rates_id, rates.rate AS rates_rate,
                       base.id AS base_id, base.code AS base_code, base.fullname AS base_fullname, base.sign AS base_sign,
                       target.id AS target_id, target.code AS target_code, target.fullname AS target_fullname, target.sign AS target_sign
                FROM exchange_rates rates
                JOIN currencies base ON rates.base_currency_id = base.id
                JOIN currencies target ON rates.target_currency_id = target.id
                ORDER BY rates_id
                """;

        List<ExchangeRate> result = new ArrayList<>();
        try (Connection connection = manager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                result.add(map(resultSet));
            }

        } catch (SQLException e) {
            throw Translator.translate("Не удалось получить список обменных курсов.", e);
        }

        return result;
    }

    @Override
    public Optional<ExchangeRate> findByCodes(String baseCode, String targetCode) {

        String sql = """
                SELECT rates.id AS rates_id, rates.rate AS rates_rate,
                       base.id AS base_id, base.code AS base_code, base.fullname AS base_fullname, base.sign AS base_sign,
                       target.id AS target_id, target.code AS target_code, target.fullname AS target_fullname, target.sign AS target_sign
                FROM exchange_rates rates
                JOIN currencies base ON rates.base_currency_id = base.id
                JOIN currencies target ON rates.target_currency_id = target.id
                WHERE base.code = ? AND target.code = ?
                ORDER BY rates_id
                """;

        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, baseCode);
            preparedStatement.setString(2, targetCode);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(map(resultSet));
                }
            }

        } catch (SQLException e) {
            throw Translator.translate("Не удалось получить обменный курс.", e);
        }

        return Optional.empty();
    }

    @Override
    public ExchangeRate save(ExchangeRate exchangeRate) {

        /*
        СЕРВИС должен проверять на существование обеих валют в exchangeRate, перед тем как
        передавать в этот метод
         */

        String sql = """
                INSERT INTO exchange_rates(base_currency_id, target_currency_id, rate)
                VALUES (?, ?, ?)
                RETURNING id
                """;

        Currency baseCurrency = exchangeRate.baseCurrency();
        Currency targetCurrency = exchangeRate.targetCurrency();
        BigDecimal rate = exchangeRate.rate();
        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, baseCurrency.id());
            preparedStatement.setInt(2, targetCurrency.id());
            preparedStatement.setBigDecimal(3, rate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    return new ExchangeRate(id, baseCurrency, targetCurrency, rate);
                }
                throw new DaoException("Ошибка при сохранении курса.");
            }

        } catch (SQLException e) {
            throw Translator.translate("Не удалось сохранить курс.", e);
        }
    }

    @Override
    public Optional<ExchangeRate> update(ExchangeRate exchangeRate) {
        String sql = """
                UPDATE exchange_rates
                SET rate = ?
                WHERE id = ?
                """;

        try (Connection connection = manager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setBigDecimal(1, exchangeRate.rate());
            preparedStatement.setInt(2, exchangeRate.id());

            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows == 0) {
                return Optional.empty();
            }
            return Optional.of(exchangeRate);

        } catch (SQLException e) {
            throw Translator.translate("Не удалось обновить курс.", e);
        }
    }

    private ExchangeRate map(ResultSet resultSet) throws SQLException{
        Currency base = new Currency(
                resultSet.getInt("base_id"),
                resultSet.getString("base_code"),
                resultSet.getString("base_fullname"),
                resultSet.getString("base_sign"));

        Currency target = new Currency(
                resultSet.getInt("target_id"),
                resultSet.getString("target_code"),
                resultSet.getString("target_fullname"),
                resultSet.getString("target_sign"));

        return new ExchangeRate(resultSet.getInt("rates_id"),
                base, target, resultSet.getBigDecimal("rates_rate"));

    }
}
