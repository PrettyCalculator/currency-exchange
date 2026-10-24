package com.example.service;

import com.example.dao.CurrencyDao;
import com.example.exc.dao.DaoException;
import com.example.exc.service.EntityNotFoundException;
import com.example.model.Currency;
import com.example.utils.Validator;
import com.example.utils.Normalizer;
import com.example.utils.translator.DAOExceptionTranslator;

import java.util.List;

public class CurrencyService {
    private final CurrencyDao dao;

    public CurrencyService(CurrencyDao dao) {
        this.dao = dao;
    }

    /*
    Не ловим исключения валидации потому что это уже и есть сервисное исключение,
    и его не нужно преобразовывать. Сервисными исключениями займется сервлет.
     */

    public List<Currency> getCurrencies() {
        try {
            return dao.findAll();
        } catch (DaoException e) {
            throw DAOExceptionTranslator.translate("Не удалось получить список валют", e);
        }
    }

    public Currency getByCode(String code) {

        Validator.validateCode(code);
        code = Normalizer.code(code);

        try {
            return dao.findByCode(code).orElseThrow(
                    () -> new EntityNotFoundException("Валюта отсутствует в хранилище."));

        } catch (DaoException e) {
            throw DAOExceptionTranslator.translate("Не удалось получить валюту по коду: " + code, e);
        }
    }

    public Currency create(String code, String fullName, String sign) {

        Validator.validateCode(code);
        Validator.validateFullName(fullName);
        Validator.validateSign(sign);

        String normalizedCode = Normalizer.code(code);
        String normalizedFullName = Normalizer.fullName(fullName);
        String normalizedSign = Normalizer.sign(sign);

        try {
            return dao.save(new Currency(null, normalizedCode, normalizedFullName, normalizedSign));

        } catch (DaoException e) {
            throw DAOExceptionTranslator.translate("Не удалось сохранить валюту." , e);
        }
    }

}
