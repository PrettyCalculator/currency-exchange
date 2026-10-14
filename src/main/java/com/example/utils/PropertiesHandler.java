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
        return properties.getProperty(key);
    }


    public static void loadProperties() {
        try (InputStream is = PropertiesHandler.class.
                getClassLoader().
                getResourceAsStream("application.properties")) {

            properties.load(is);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PropertiesHandler() {}

}
