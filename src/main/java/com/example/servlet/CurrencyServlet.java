package com.example.servlet;

import com.example.model.Currency;
import com.example.service.CurrencyService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/currency/*")
public class CurrencyServlet extends BaseServlet {
    private CurrencyService service;

    @Override
    public void init() {
        super.init();
        service = (CurrencyService) getServletContext().getAttribute(Attributes.CURRENCY_SERVICE.value());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String code = getPathParameter(request, "Пустой код валюты.");
        Currency currency = service.getByCode(code);
        sendJson(response, currency);
    }
}
