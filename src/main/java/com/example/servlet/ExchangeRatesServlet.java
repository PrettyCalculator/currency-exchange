package com.example.servlet;

import com.example.model.ExchangeRate;
import com.example.service.ExchangeRateService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/exchangeRates")
public class ExchangeRatesServlet extends BaseServlet {
    private ExchangeRateService exchangeRateService;

    @Override
    public void init() {
        super.init();
        exchangeRateService = (ExchangeRateService) getServletContext().getAttribute(Attributes.EXCHANGE_RATE_SERVICE.value());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)  throws IOException {
        request.setCharacterEncoding("UTF-8");
        String baseCurrencyCode = request.getParameter("baseCurrencyCode");
        String targetCurrencyCode = request.getParameter("targetCurrencyCode");
        BigDecimal rate = parseBigDecimal(request.getParameter("rate"));

        ExchangeRate saved = exchangeRateService.create(baseCurrencyCode, targetCurrencyCode, rate);

        response.setStatus(201);
        sendJson(response, saved);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        List<ExchangeRate> exchangeRateList = exchangeRateService.getExchangeRates();

        response.setStatus(200);
        sendJson(response, exchangeRateList);
    }
}
