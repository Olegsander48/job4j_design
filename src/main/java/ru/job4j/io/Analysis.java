package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            StringBuilder builder = new StringBuilder();
            for (String element : reader.lines().toList()) {
                if (builder.isEmpty() && (element.startsWith("400") || element.startsWith("500"))) {
                    builder.append(element.substring(element.indexOf(" ") + 1)).append(";");
                } else if (!builder.isEmpty() && (element.startsWith("200") || element.startsWith("300"))) {
                    builder.append(element.substring(element.indexOf(" ") + 1)).append(";");
                    result.add(builder.toString());
                    builder = new StringBuilder();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter reader = new BufferedWriter(new FileWriter(target))) {
            for (String err : result) {
                reader.write(err + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
