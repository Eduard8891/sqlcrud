package org.example;

import org.example.repository.PostgresConnection;
import org.example.view.MainView;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        PostgresConnection.create();
        MainView mainView = new MainView();
        mainView.start();
    }
}
