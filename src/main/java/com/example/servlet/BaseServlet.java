package com.example.servlet;

import com.example.exc.service.ServiceException;
import com.example.model.ErrorModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseServlet extends HttpServlet {
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

    public void sendErrorJson(HttpServletResponse response, int code, String message) {
        response.setStatus(code);
        sendJson(response, new ErrorModel(message));
    }
}
