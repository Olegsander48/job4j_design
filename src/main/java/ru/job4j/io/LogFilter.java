package ru.job4j.io;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            return input.lines()
                    .filter(str -> str
                            .split(" ")[8]
                            .contains("404"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out)
                ))) {
            for (var element : data) {
                output.println(element);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}
