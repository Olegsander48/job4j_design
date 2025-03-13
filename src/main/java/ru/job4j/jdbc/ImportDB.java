package ru.job4j.jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Properties config;
    private String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines().forEach(el -> {
                checkString(el);
                users.add(new User(
                        el.substring(0, el.indexOf(';')),
                        el.substring(el.indexOf(';') + 1, el.lastIndexOf(';'))));
            });
        }
        return users;
    }

    private void checkString(String element) {
        if (element.isEmpty() || element.indexOf(';') == 0) {
            throw new IllegalArgumentException("No parameters: expected 2, but was 0");
        }
        if (element.indexOf(';') == -1) {
            throw new IllegalArgumentException("No delimiters in the string");
        }
        if (element.substring(element.indexOf(';')).length() == 1) {
            throw new IllegalArgumentException("Missing email");
        }
        if (element.indexOf(';') == element.lastIndexOf(';')) {
            throw new IllegalArgumentException("No ending delimiter in the string after email");
        }
        if (!element.substring(element.indexOf(';') + 1).contains("@")) {
            throw new IllegalArgumentException("No at symbol(@) in the email");
        }
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(config.getProperty("jdbc.url"), config.getProperty("jdbc.username"), config.getProperty("jdbc.password"))) {
            for (User user : users) {
                try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name, email) VALUES (?, ?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, "data/dump.txt");
        dataBase.save(dataBase.load());
    }
}
