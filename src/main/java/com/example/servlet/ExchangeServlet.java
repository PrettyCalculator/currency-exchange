package com.example.servlet;

import com.example.model.ConversionResult;
import com.example.service.ConversionService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

@WebServlet("/exchange")
public class ExchangeServlet extends BaseServlet {
    private ConversionService conversionService;

    @Override
    public void init() {
        super.init();
        conversionService = (ConversionService) getServletContext().getAttribute(Attributes.CONVERSION_SERVICE.value());
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String baseCurrencyCode = request.getParameter("from");
        String targetCurrencyCode = request.getParameter("to");
        BigDecimal amount = parseBigDecimal(request.getParameter("amount"));
        ConversionResult result = conversionService.convert(baseCurrencyCode, targetCurrencyCode, amount);

        sendJson(response, result);
    }
}
