package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String value = values.get(key);
        if (value == null) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return value;
    }

    private void parse(String[] args) {
        for (String argument : args) {
            checkInputValue(argument);
            values.put(
                    argument.substring(
                            argument.indexOf("-") + 1,
                            argument.indexOf("=")),
                    argument.substring(argument.indexOf("=") + 1)
            );
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private static void checkInputValue(String str) {
        if (!str.startsWith("-")) {
            throw new IllegalArgumentException("Error: This argument '" + str + "' does not start with a '-' character");
        }
        if (!str.contains("=")) {
            throw new IllegalArgumentException("Error: This argument '" + str + "' does not contain an equal sign");
        }
        if (str.substring(
                    str.indexOf("-") + 1,
                    str.indexOf("="))
                .isEmpty()) {
            throw new IllegalArgumentException("Error: This argument '" + str + "' does not contain a key");
        }
        if (str.substring(
                str.indexOf("=") + 1)
                .isEmpty()) {
            throw new IllegalArgumentException("Error: This argument '" + str + "' does not contain a value");
        }
    }

    public int getMapSize() {
        return values.size();
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        System.out.println(jvm.get("encoding"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
        System.out.println(zip.get("encoding"));
    }
}
