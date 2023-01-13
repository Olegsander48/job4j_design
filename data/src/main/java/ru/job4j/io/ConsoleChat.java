package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
        List<String> botPhrases = readPhrases();
        List<String> logs = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String userAnswer = "";
        String botAnswer;

        boolean flag = false;

        while (!OUT.equals(userAnswer)) {
            System.out.println("Input your phrase: ");
            userAnswer = scanner.nextLine();
            logs.add(userAnswer);
            if (STOP.equals(userAnswer)) {
                flag = true;
            }
            if (CONTINUE.equals(userAnswer)) {
                flag = false;
            }
            if (flag) {
                continue;
            }
            botAnswer = botPhrases.get((int) (Math.random() * botPhrases.size()));
            System.out.println(botAnswer);
            logs.add(botAnswer);
        }

        saveLog(logs);
    }

    private List<String> readPhrases() {
        List<String> phrases = null;
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            phrases = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/dialogue.log", "data/bot_answers.txt");
        cc.run();
    }
}
