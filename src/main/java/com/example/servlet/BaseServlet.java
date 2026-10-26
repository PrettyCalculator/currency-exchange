package com.example.servlet;

import com.example.exc.service.ServiceException;
import com.example.exc.service.ValidationException;
import com.example.model.ErrorModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        objectMapper = (ObjectMapper) getServletContext().getAttribute(Attributes.OBJECT_MAPPER.value());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            super.service(request, response);
        } catch (ServiceException e) {
            sendErrorJson(response, e.code, e.getMessage());
            if (e.code >= 500) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        } catch (Exception e) {
            sendErrorJson(response, 500, "Внутренняя ошибка сервера.");
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void sendJson(HttpServletResponse response, Object object) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            objectMapper.writer().writeValue(response.getOutputStream(), object);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void sendErrorJson(HttpServletResponse response, int code, String message) {
        response.setStatus(code);
        sendJson(response, new ErrorModel(message));
    }

    /**
     * Разбирает строковый параметр запроса как число.
     * Здесь проверяется только формат; допустимость значения проверяет сервис.
     */
    protected BigDecimal parseBigDecimal(String value) {
        if (value == null || value.isBlank()) {
            throw new ValidationException("Отсутствует числовой параметр запроса.");
        }
        try {
            return new BigDecimal(value.trim());
        } catch (NumberFormatException e) {
            throw new ValidationException("Параметр запроса должен быть числом.");
        }
    }

    /**
     * Возвращает часть адреса после шаблона сервлета без ведущего слэша:
     * для "/currency/EUR" при маппинге "/currency/*" вернёт "EUR".
     *
     * @param missingMessage текст ошибки 400, если в адресе ничего нет
     */
    protected String getPathParameter(HttpServletRequest request, String missingMessage) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            throw new ValidationException(missingMessage);
        }
        return pathInfo.substring(1);
    }

    protected Map<String, String> readAndParseRequestBody(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String body = sb.toString();
        if (body.isEmpty()) {
            throw new ValidationException("Пустое тело запроса");
        }

        String[] parts = body.split("&");
        Map<String, String> map = new HashMap<>();
        for (String part: parts) {
            if (part.contains("=")) {
                String[] p = part.split("=", 2);
                String key = URLDecoder.decode(p[0], StandardCharsets.UTF_8);
                String value = URLDecoder.decode(p[1], StandardCharsets.UTF_8);
                map.put(key, value);
            }
        }

        return map;
    }
}
