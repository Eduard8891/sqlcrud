package org.example;

import org.example.view.MainView;

import java.sql.*;
import java.time.Instant;
import java.util.Calendar;

public class App {
    public static void main(String[] args) throws SQLException {
        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        System.out.println(date);
//        MainView mainView = new MainView();
//        mainView.start();
    }
}
