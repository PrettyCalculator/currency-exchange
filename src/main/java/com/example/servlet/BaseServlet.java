package com.example.servlet;

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
            super.service(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e); // вместо этого будет обработка исключений
        }
    }

    public void sendJson(HttpServletRequest request, HttpServletResponse response, Object object) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            objectMapper.writer().writeValue(response.getOutputStream(), object);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
