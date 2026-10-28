package com.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesHandler {
    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    public static String getProperty(String key) {
        String value = System.getenv(key);
        if (value == null) {

            value = properties.getProperty(key);

            if (value == null) {
                throw new RuntimeException("Не удалось получить переменную конфигурации: " + key);
            }
        }
        return value;
    }


    private static void loadProperties() {
        try (InputStream is = PropertiesHandler.class.
                getClassLoader().
                getResourceAsStream("application.properties")) {
            if (is != null) {
                properties.load(is);
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить файл конфигурации.", e);
        }
    }

    private PropertiesHandler() {}

}
