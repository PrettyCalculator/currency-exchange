package com.example;

import com.example.dao.CurrencyDao;
import com.example.dao.ExchangeRateDao;
import com.example.dao.impl.CurrencyDaoJdbc;
import com.example.dao.impl.ExchangeRateDaoJdbc;
import com.example.model.Currency;
import com.example.service.ConversionService;
import com.example.service.CurrencyService;
import com.example.service.ExchangeRateService;
import com.example.servlet.Attributes;
import com.example.utils.ConnectionManager;
import com.example.utils.PropertiesHandler;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import javax.print.attribute.standard.PagesPerMinute;

@WebListener
public class AppInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionManager connectionManager = new ConnectionManager();
        CurrencyDao currencyDao = new CurrencyDaoJdbc(connectionManager);
        ExchangeRateDao exchangeRateDao = new ExchangeRateDaoJdbc(connectionManager);
        ConversionService conversionService = new ConversionService(exchangeRateDao, currencyDao);
        CurrencyService currencyService = new CurrencyService(currencyDao);
        ExchangeRateService exchangeRateService = new ExchangeRateService(exchangeRateDao, currencyDao);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);

        ServletContext context = sce.getServletContext();
        context.setAttribute(Attributes.CONNECTION_MANAGER.value(), connectionManager);
        context.setAttribute(Attributes.CONVERSION_SERVICE.value(), conversionService);
        context.setAttribute(Attributes.CURRENCY_SERVICE.value(), currencyService);
        context.setAttribute(Attributes.EXCHANGE_RATE_SERVICE.value(), exchangeRateService);
        context.setAttribute(Attributes.OBJECT_MAPPER.value(), objectMapper);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionManager connectionManager = (ConnectionManager) sce.getServletContext().getAttribute(Attributes.CONNECTION_MANAGER.value());
        if (connectionManager != null) {
            connectionManager.closeDataSource();
        }
    }
}
