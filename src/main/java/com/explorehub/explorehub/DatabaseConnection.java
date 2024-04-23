package com.explorehub.explorehub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/explorehub"; // Update with your database URL
    private static final String USER = "root"; // Update with your database username
    private static final String PASSWORD = ""; // Update with your database password

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
