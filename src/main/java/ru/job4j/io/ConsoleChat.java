package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        List<String> log = new ArrayList<>();
        String userInput = null;
        while (!OUT.equals(userInput)) {
            System.out.print("Input your phrase ");
            userInput = scanner.nextLine();
            log.add(userInput + System.lineSeparator());
            if (STOP.equals(userInput)) {
                flag = true;
            }
            if (CONTINUE.equals(userInput)) {
                flag = false;
            }
            if (!flag) {
                String phrase = readPhrases().get((int) (Math.random() * readPhrases().size()));
                System.out.println(phrase);
                log.add(phrase + System.lineSeparator());
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases;
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            phrases = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String s : log) {
                writer.write(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/chat/conversation.log", "data/chat/phrases.txt");
        consoleChat.run();
    }
}
