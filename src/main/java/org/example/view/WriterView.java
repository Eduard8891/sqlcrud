package org.example.view;

import org.example.controller.WriterController;

import java.io.PrintStream;
import java.util.Scanner;

public class WriterView {
    private final PrintStream printStream;
    private final Scanner scanner;
    private final WriterController writerController;

    public WriterView() {
        this.printStream = System.out;
        this.scanner = new Scanner(System.in);
        this.writerController = new WriterController();
    }

    public void selectCommand() {
        defaultText();
        while (true) {
            String line = readLine();
            if (line.equalsIgnoreCase("Обратно")) {
                return;
            } else if (line.equalsIgnoreCase("Показать всех")) {
                writerController.getAll().forEach(printStream::println);
                continue;
            }
            if (!line.isEmpty() && line.split(" ").length >= 2 && line.split(" ").length < 5) {
                String command = line.split(" ")[0];
                String body = line.split(" ", 2)[1];
                switch (command.toLowerCase()) {
                    case "создать": {
                        boolean success = writerController.createWriter(body);
                        if (success) success();
                        continue;
                    }
                    case "удалить": {
                        writerController.deleteWriter(Integer.valueOf(body));
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
        printStream.println("Вы выбрали 'Автор'! \nТеперь укажите команду: \nСоздать 'ФИО_автора'\nУдалить 'ИД_автора'\nПоказать всех\nОбратно");
    }

    public void success() {
        printStream.println("Команда выполнена успешно!");
    }

    public void errorText(String line) {
        printStream.printf("Вы допустили ошибку в команде для 'Автор': %s\n", line);
    }

}
