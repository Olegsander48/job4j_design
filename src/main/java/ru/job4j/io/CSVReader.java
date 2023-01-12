package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        checkParams(argsName);
        StringBuilder sb = new StringBuilder();

        String[] filters = argsName.get("filter").split(",");
        int[] indexesFilters = new int[filters.length];


        try (var scanner = new Scanner(new FileInputStream(argsName.get("path")))) {
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                String[] data = str.split(argsName.get("delimiter"));

                for (int i = 0; i < filters.length; i++) {
                    for (int j = 0; j < data.length; j++) {
                        if (filters[i].equals(data[j])) {
                            indexesFilters[i] = j;
                        }
                    }
                }

                for (int i : indexesFilters) {
                    sb.append(data[i]).append(argsName.get("delimiter"));
                }
                sb.deleteCharAt(sb.length() - 1)
                        .append(System.lineSeparator());
            }
        }

        if (argsName.get("out").equals("stdout")) {
            System.out.println(sb);
        } else {
            try (PrintStream out = new PrintStream(new FileOutputStream(argsName.get("out")))) {
                out.print(sb);
            } catch (Exception e) {
                e.printStackTrace();
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

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
