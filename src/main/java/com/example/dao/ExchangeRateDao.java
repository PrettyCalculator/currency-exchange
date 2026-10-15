package com.example.dao;

import com.example.model.ExchangeRate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ExchangeRateDao {
    List<ExchangeRate> findAll();
    Optional<ExchangeRate> findByCodes(String baseCode, String targetCode);

    /*
    Сервис, перед тем как вызвать save или update, проверяет, есть ли в базе данных
    валюты с такими code. Если есть, то упаковывает их в exchangeRate и вызывает сами методы
     */
    ExchangeRate save(ExchangeRate exchangeRate);

    /*
    Меняет только rate у записи с exchangeRate.id() (id обязателен, не null).
    Возвращает переданный объект, если запись найдена, и Optional.empty(), если записи с таким id нет.
    Сервис получает id и валюты через findByCodes, подставляет новый rate и вызывает этот метод.
     */
    Optional<ExchangeRate> update(ExchangeRate exchangeRate);

}
