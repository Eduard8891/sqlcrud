package org.example.repository;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {

    private static final String URL = "";
    private static final String USER = "";
    private static final String PASSWORD = "";
    private static Connection connection = null;

    public static Connection createOrGetConnection() {
        if (connection == null) {
            try {
                Driver driver = new Driver();
                DriverManager.registerDriver(driver);
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
