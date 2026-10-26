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
        sendJson(res, currencies);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String code = req.getParameter("code");
        String sign = req.getParameter("sign");

        Currency currency = currencyService.create(code, name, sign);

        res.setStatus(201);
        sendJson(res, currency);
    }

}
