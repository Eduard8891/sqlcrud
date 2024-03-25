package org.example.view;



import org.example.controller.PostController;

import java.io.PrintStream;
import java.util.Scanner;

public class PostView {
    private final PrintStream printStream;
    private final Scanner scanner;
    private final PostController postController;

    public PostView() {
        this.printStream = System.out;
        this.scanner = new Scanner(System.in);
        this.postController = new PostController();
    }

    public void selectCommand() {
        defaultText();
        while (true) {
            String line = readLine();
            if (line.equalsIgnoreCase("Обратно")) {
                return;
            } else if (line.equalsIgnoreCase("Показать все")) {
                postController.getAll().forEach(printStream::println);
                continue;
            }
            if (!line.isEmpty()) {
                String command = line.split(" ")[0];
                String body = line.split(" ", 2)[1];
                switch (command.toLowerCase()) {
                    case "создать": {
                        String tags = body.replaceAll(" ", "").split("___")[1].replaceAll(",", "");
                        if (tags.matches("\\d+")) {
                            boolean success = postController.createPost(body);
                            if (success) success();
                        } else {
                            errorText(line);
                        }
                        continue;
                    }
                    case "редактировать": {
                        boolean success = postController.updatePost(body);
                        if (success) success();
                        continue;
                    }
                    case "удалить": {
                        postController.deletePost(Integer.valueOf(body));
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
        printStream.println("Вы выбрали 'Пост'! \nТеперь укажите команду: \nСоздать 'Текст_поста___теги_через_запятую'" +
                "\nРедактировать 'ИД_поста___текст_поста___теги_через_запятую'\nУдалить 'ИД_поста'\nПоказать все\nОбратно");
    }

    public void success() {
        printStream.println("Команда выполнена успешно!");
    }

    public void errorText(String line) {
        printStream.printf("Вы допустили ошибку в команде для 'метка': %s\n", line);
    }

}
