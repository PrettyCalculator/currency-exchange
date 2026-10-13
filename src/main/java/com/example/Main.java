package com.example;


import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:8080/currency_db";
        String user = "postgres";
        String password = "1234";


        try (Connection con = DriverManager.getConnection(url, user, password)) {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT 1");

            while (result.next()) {
                System.out.println(result.getInt(1));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}