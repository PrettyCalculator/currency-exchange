package com.example.servlet;

import com.example.exc.service.ServiceException;
import com.example.model.ErrorModel;
import com.example.utils.translator.ServiceExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class BaseServlet extends HttpServlet {

    private ObjectMapper objectMapper;

    @Override
    public void init() {
        objectMapper = (ObjectMapper) getServletContext().getAttribute(Attributes.OBJECT_MAPPER.value());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (request.getMethod().equals("PATCH")) {

            }
            super.service(request, response);
        } catch (ServiceException e) {
            sendErrorJson(response, e.code, e.getMessage());
        } catch (Exception e) {
            sendErrorJson(response, 500, "Внутренняя ошибка сервера.");
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
        try {
            response.setStatus(code);
            objectMapper.writer().writeValue(response.getOutputStream(), new ErrorModel(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
