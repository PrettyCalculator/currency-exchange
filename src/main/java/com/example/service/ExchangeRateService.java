package com.example.service;

import com.example.dao.CurrencyDao;
import com.example.dao.ExchangeRateDao;
import com.example.exc.dao.DaoException;
import com.example.exc.service.EntityNotFoundException;
import com.example.exc.service.ServiceException;
import com.example.exc.service.ValidationException;
import com.example.model.Currency;
import com.example.model.ExchangeRate;
import com.example.utils.Validator;
import com.example.utils.Normalizer;
import com.example.utils.translator.DAOExceptionTranslator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

public class ExchangeRateService {
    private static final String USD_CODE = "USD";
    private final ExchangeRateDao exchangeRateDao;
    private final CurrencyDao currencyDAO;

    public ExchangeRateService(ExchangeRateDao exchangeRateDao, CurrencyDao currencyDAO) {
        this.exchangeRateDao = exchangeRateDao;
        this.currencyDAO = currencyDAO;
    }

    public List<ExchangeRate> getExchangeRates() {
        try {
            return exchangeRateDao.findAll();
        } catch (DaoException e) {
            throw DAOExceptionTranslator.translate("Не удалось получить список курсов валют.", e);
        }
    }

    public ExchangeRate getByCodes(String codePair) {
        try {
            String[] pair = parseCodePair(codePair);

            Validator.validateCode(pair[0]);
            Validator.validateCode(pair[1]);
            String baseCode = Normalizer.code(pair[0]);
            String targetCode = Normalizer.code(pair[1]);

            return exchangeRateDao.findByCodes(baseCode, targetCode).orElseThrow(() -> new EntityNotFoundException(String.format("Курс из %s в %s не найден.", baseCode, targetCode)));

        } catch (DaoException e) {
            throw DAOExceptionTranslator.translate("Не удалось получить курс.", e);
        }
    }

    public ExchangeRate create(String base, String target, BigDecimal rate) {
        try {

            Validator.validateCode(base);
            Validator.validateCode(target);
            Validator.validateRate(rate);
            String baseCode = Normalizer.code(base);
            String targetCode = Normalizer.code(target);
            checkCurrencyCodesEquals(base, target);

            // проверить наличие валюты base
            Currency baseCurrency = currencyDAO.findByCode(baseCode).orElseThrow(
                    () -> new EntityNotFoundException(String.format("Валюта %s не найдена", baseCode)));

            // проверить наличие валюты target
            Currency targetCurrency = currencyDAO.findByCode(targetCode).orElseThrow(
                    () -> new EntityNotFoundException(String.format("Валюта %s не найдена", targetCode)));

            // упаковать в exchangeRate и отправить
            return exchangeRateDao.save(new ExchangeRate(null, baseCurrency, targetCurrency, rate));
        } catch (DaoException e) {
            throw DAOExceptionTranslator.translate("Не удалось сохранить курс.", e);
        }
    }

    public ExchangeRate update(String codePair, BigDecimal rate) {
        try {

            String[] pair = parseCodePair(codePair);

            Validator.validateCode(pair[0]);
            Validator.validateCode(pair[1]);
            Validator.validateRate(rate);
            String baseCode = Normalizer.code(pair[0]);
            String targetCode = Normalizer.code(pair[1]);

            checkCurrencyCodesEquals(baseCode, targetCode);

            ExchangeRate exchangeRate = exchangeRateDao.findByCodes(baseCode, targetCode).
                    orElseThrow(() -> new EntityNotFoundException("Обменный курс не найден."));

            return exchangeRateDao.update(new ExchangeRate(exchangeRate.id(), exchangeRate.baseCurrency(), exchangeRate.targetCurrency(), rate))
                    .orElseThrow(() -> new ServiceException("Ошибка обновления записи."));
        } catch (DaoException e) {
            throw DAOExceptionTranslator.translate("Не удалось обновить курс.", e);
        }

    }

    private String[] parseCodePair(String codePair) {
        if (codePair != null && codePair.length() == 6) {
            return new String[]{codePair.substring(0, 3), codePair.substring(3)};

        }

        throw new ValidationException("Неверный формат кодовой пары.");
    }

    private BigDecimal findRateViaUSD(String baseCode, String targetCode) {
        BigDecimal baseToUsdRate = null;
        BigDecimal usdToTargetRate = null;

        Optional<ExchangeRate> baseToUsd = exchangeRateDao.findByCodes(baseCode, USD_CODE);
        if (baseToUsd.isPresent()) {
            baseToUsdRate = baseToUsd.get().rate();
        }
        else {
            Optional<ExchangeRate> usdToBase = exchangeRateDao.findByCodes(USD_CODE, baseCode);
            if (usdToBase.isPresent()) {
                baseToUsdRate = BigDecimal.ONE.divide(usdToBase.get().rate(), 10, RoundingMode.HALF_UP);
            }
        }


        Optional<ExchangeRate> usdToTarget = exchangeRateDao.findByCodes(USD_CODE, targetCode);
        if (usdToTarget.isPresent()) {
            usdToTargetRate = usdToTarget.get().rate();
        } else {
            Optional<ExchangeRate> targetToUsd = exchangeRateDao.findByCodes(targetCode, USD_CODE);
            if (targetToUsd.isPresent()) {
                usdToTargetRate = BigDecimal.ONE.divide(targetToUsd.get().rate(), 10, RoundingMode.HALF_UP);
            }
        }

        return baseToUsdRate != null && usdToTargetRate != null ? baseToUsdRate.multiply(usdToTargetRate) : BigDecimal.ZERO;
    }

    private void checkCurrencyCodesEquals(String code1, String code2) {
        if (code1.equals(code2)) {
            throw new ValidationException("Одинаковые исходный и целевой курсы валют.");
        }
    }
}
