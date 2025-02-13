package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        checkParams(argsName);
        StringBuilder builder = new StringBuilder();
        String[] filters = argsName.get("filter").split(",");
        int[] indexes = new int[filters.length];

        try (Scanner scanner = new Scanner(new FileReader(argsName.get("path")))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(argsName.get("delimiter"));

                for (int i = 0; i < filters.length; i++) {
                    for (int j = 0; j < values.length; j++) {
                        if (filters[i].equals(values[j])) {
                            indexes[i] = j;
                        }
                    }
                }
                for (int i : indexes) {
                    builder.append(values[i]).append(argsName.get("delimiter"));
                }
                builder.deleteCharAt(builder.length() - 1).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (argsName.get("out").equals("stdout")) {
            System.out.println(builder);
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(argsName.get("out")))) {
                writer.write(builder.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void checkParams(ArgsName argsName) {
        File file = new File(argsName.get("path"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("File not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException(String.format("Not file %s", file.getAbsoluteFile()));
        }
        File outFile = new File(argsName.get("out"));
        if (!argsName.get("out").equals("stdout") && !outFile.isAbsolute()) {
            throw new IllegalArgumentException("Incorrect form of data output. Use stdout for console output or file path to save data to file");
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
