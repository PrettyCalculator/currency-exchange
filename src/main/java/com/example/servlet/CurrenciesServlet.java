package com.example.servlet;

import com.example.service.CurrencyService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import com.example.model.Currency;
import java.util.List;

@WebServlet("/currencies")
public class CurrenciesServlet extends BaseServlet {
    private CurrencyService currencyService;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        currencyService = (CurrencyService) context.getAttribute(Attributes.CURRENCY_SERVICE.value());
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<Currency> currencies = currencyService.getCurrencies();
        sendJson(req, res, currencies);
    }
}
