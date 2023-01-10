package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException();
        }
        return value;
    }

    private void parse(String[] args) {
        for (String str : args) {
            checkString(str);
            values.put(
                    str.substring(str.indexOf("-") + 1, str.indexOf("=")),
                    str.substring(str.indexOf("=") + 1));
        }
    }

    private void checkString(String str) {
        if (!str.startsWith("-")
                || !str.contains("=")
                || str.substring(str.indexOf("-") + 1, str.indexOf("=")).isBlank()
                || str.substring(str.indexOf("=") + 1).isBlank()) {
            throw new IllegalArgumentException(String.format("String doesn't match pattern: -key=value. Input string is: %s", str));
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0
                || Arrays.stream(args)
                    .anyMatch(str -> !str.startsWith("-o")
                            && !str.startsWith("-d")
                            && !str.startsWith("-e"))) {
            throw new IllegalArgumentException();
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("encoding"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
