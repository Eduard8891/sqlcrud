package org.example.view;


import org.example.controller.LabelController;

import java.io.PrintStream;
import java.util.Scanner;

public class LabelView {
    private final PrintStream printStream;
    private final Scanner scanner;
    private final LabelController labelController;


    public LabelView() {
        this.printStream = System.out;
        this.scanner = new Scanner(System.in);
        this.labelController = new LabelController();
    }

    public void selectCommand() {
        defaultText();
        while (true) {
            String line = readLine();
            if (line.equalsIgnoreCase("Обратно")) {
                return;
            } else if (line.equalsIgnoreCase("Показать все")) {
                labelController.getAll().forEach(printStream::println);
                continue;
            }
            if (!line.isEmpty() && line.split(" ").length == 2) {
                String command = line.split(" ")[0];
                String body = line.split(" ")[1];
                switch (command.toLowerCase()) {
                    case "создать": {
                        boolean success = labelController.createLabel(body);
                        if (success) success();
                        continue;
                    }
                    case "удалить": {
                        labelController.deleteLabel(Integer.valueOf(body));
                        continue;
                    }
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
        printStream.println("Вы выбрали 'Метка'! \nТеперь укажите команду: \nСоздать 'имя_тега'\nУдалить 'ИД_тега'\nПоказать все\nОбратно");
    }

    public void success() {
        printStream.println("Команда выполнена успешно!");
    }

    public void errorText(String line) {
        printStream.printf("Вы допустили ошибку в команде для 'метка': %s\n", line);
    }

}
