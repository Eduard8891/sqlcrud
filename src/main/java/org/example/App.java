package org.example;

import org.example.view.MainView;

public class App {
    public static void main(String[] args) {
        FlywayUtils.initFlyWay();
        MainView mainView = new MainView();
        mainView.start();
    }
}
