package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines()
                    .filter(this::checkString)
                    .forEach(el -> values.put(el.substring(0, el.indexOf("=")),
                            el.substring(el.indexOf("=") + 1)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    private boolean checkString(String value) {
        boolean result = !value.isBlank() && !value.startsWith("#");
        if (result
                && (!value.contains("=")
                    || value.substring(0, value.indexOf("=")).isBlank()
                    || value.substring(value.indexOf("=") + 1).isBlank())) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
        new Config("data/app.properties").load();

    }
}
