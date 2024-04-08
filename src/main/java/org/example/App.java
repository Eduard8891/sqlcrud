package org.example;

import org.example.view.MainView;


public class App {
    public static void main(String[] args) {
        PostgresConnection.create();
        MainView mainView = new MainView();
        mainView.start();
    }
}
