package com.example.service;

import com.example.dao.CurrencyDao;
import com.example.dao.ExchangeRateDao;
import com.example.exc.dao.DaoException;
import com.example.exc.service.EntityNotFoundException;
import com.example.model.ConversionResult;
import com.example.model.Currency;
import com.example.model.ExchangeRate;
import com.example.utils.Normalizer;
import com.example.utils.Validator;
import com.example.utils.translator.DAOExceptionTranslator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Optional;

public class ConversionService {
    private static final String USD_CODE = "USD";

    private final ExchangeRateDao exchangeRateDao;
    private final CurrencyDao currencyDao;

    public ConversionService(ExchangeRateDao dao, CurrencyDao currencyDAO) {
        this.exchangeRateDao = dao;
        this.currencyDao = currencyDAO;
    }

    public ConversionResult convert(String from, String to, BigDecimal amount) {

        try {
            Validator.validateCode(from);
            Validator.validateCode(to);
            Validator.validateAmount(amount);

            String base = Normalizer.code(from);
            String target = Normalizer.code(to);

            if (base.equals(target)) {
                Currency currency = currencyDao.findByCode(base)
                        .orElseThrow(() -> new EntityNotFoundException("Валюта не найдена."));
                return new ConversionResult(currency, currency, BigDecimal.ONE, amount, amount);
            }

            Currency baseCurrency = currencyDao.findByCode(base)
                    .orElseThrow(() -> new EntityNotFoundException("Одна из валют не найдена."));

            Currency targetCurrency = currencyDao.findByCode(target)
                    .orElseThrow(() -> new EntityNotFoundException("Одна из валют не найдена."));

            BigDecimal rate = findDirectOrReversed(base, target)
                    .or(() -> findRateViaUsd(base, target))
                    .orElseThrow(() -> new EntityNotFoundException("Обменный курс для пары не найден."));

            BigDecimal convertedAmount = amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);

            return new ConversionResult(baseCurrency, targetCurrency, rate.setScale(2, RoundingMode.HALF_UP), amount, convertedAmount);

        } catch (DaoException e) {
            throw DAOExceptionTranslator.translate("Ошибка конвертации", e);
        }
    }

    private Optional<BigDecimal> findDirectOrReversed(String base, String target) {
        return exchangeRateDao.findByCodes(base, target).map(ExchangeRate::rate)
                .or(() -> exchangeRateDao.findByCodes(target, base)
                        .map(ExchangeRate::rate)
                        .map((a) -> BigDecimal.ONE.divide(a, new MathContext(10))));
    }


    private Optional<BigDecimal> findRateViaUsd(String baseCode, String targetCode) {

        Optional<BigDecimal> toTarget = findDirectOrReversed(USD_CODE, targetCode);
        Optional<BigDecimal> toBase = findDirectOrReversed(USD_CODE, baseCode);
        return toBase.isPresent() && toTarget.isPresent() ?
                Optional.of(toTarget.get().divide(toBase.get(), new MathContext(10))) :
                Optional.empty();
    }

}
