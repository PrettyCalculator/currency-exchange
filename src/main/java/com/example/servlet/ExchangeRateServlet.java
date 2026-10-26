package com.example.servlet;

import com.example.exc.service.ValidationException;
import com.example.model.ExchangeRate;
import com.example.service.ExchangeRateService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

@WebServlet("/exchangeRate/*")
public class ExchangeRateServlet extends BaseServlet {
    private ExchangeRateService exchangeRateService;

    @Override
    public void init() {
        super.init();
        exchangeRateService = (ExchangeRateService) getServletContext().getAttribute(Attributes.EXCHANGE_RATE_SERVICE.value());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String codePair = getPathParameter(request, "Пустой код валютной пары не указаны");
        ExchangeRate exchangeRate = exchangeRateService.getByCodes(codePair);

        response.setStatus(200);
        sendJson(response, exchangeRate);
    }

    @Override
    public void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String codePair = getPathParameter(request, "Пустой код валютной пары не указаны");

        // получаем rate из тела запроса (парсим вручную)
        BigDecimal rate = parseBigDecimal(readAndParseRequestBody(request.getReader()).get("rate"));


        // вызываем сервис
        ExchangeRate exchangeRate = exchangeRateService.update(codePair, rate);

        response.setStatus(200);
        sendJson(response, exchangeRate);
    }
}
