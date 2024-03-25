package org.example.view;

import java.io.PrintStream;
import java.util.Scanner;

public class MainView {
    private final PrintStream printStream;
    private final Scanner scanner;
    private final LabelView labelView;
    private final PostView postView;
    private final WriterView writerView;

    public MainView() {
        this.printStream = System.out;
        this.scanner = new Scanner(System.in);
        this.labelView = new LabelView();
        this.postView = new PostView();
        this.writerView = new WriterView();

    }

    public void start() {
        while (true) {
            defaultText();
            String line = readLine();
            if (line.equalsIgnoreCase("!Стоп")) {
                return;
            }
            if (!line.isEmpty()) {
                switch (line.toLowerCase()) {
                    case "пост":
                        postView.selectCommand();
                        continue;
                    case "метка":
                        labelView.selectCommand();
                        continue;
                    case "автор":
                        writerView.selectCommand();
                        continue;
                    default:
                        errorText(line);
                }
            }
        }
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void defaultText() {
        printStream.println("Вы в главном меню.Выберите сущность:\nПост\nМетка\nАвтор");
    }

    public void errorText(String line) {
        printStream.printf("Вы допустили ошибку в команде: \n%s\n", line);
    }

}
