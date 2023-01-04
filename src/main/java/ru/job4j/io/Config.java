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
                    .filter(s1 -> !s1.isEmpty())
                    .filter(s -> !s.startsWith("#"))
                    .forEach(s -> checkString(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkString(String str) {
        if (!str.contains("=") || (str.length() == 1 && str.contains("="))) {
            throw new IllegalArgumentException();
        }

        String key = str.substring(0, str.split("=")[0].length());
        String value = str.substring(str.split("=")[0].length() + 1);
        if ((key.isEmpty() || value.isEmpty())
                || (!key.isEmpty() && !value.isEmpty() && !str.contains("="))) {
            throw new IllegalArgumentException();
        }
        values.put(key, value);
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
