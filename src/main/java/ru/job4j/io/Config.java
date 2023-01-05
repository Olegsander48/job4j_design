package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(this::checkString)
                    .forEach(str -> {
                        String key = str.substring(0, str.indexOf("="));
                        String value = str.substring(str.indexOf("=") + 1);
                        values.put(key, value);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkString(String str) {
        if ((!str.isBlank() && !str.startsWith("#") && !str.contains("="))
                || (str.length() == 1 && str.contains("="))
                || (str.contains("=") && (str.substring(0, str.indexOf("=")).isBlank()
                    || str.substring(str.indexOf("=") + 1).isBlank()))) {
            throw new IllegalArgumentException();
        }
        return !str.isBlank() && !str.startsWith("#");
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
