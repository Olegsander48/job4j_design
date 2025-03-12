package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config config = new Config("data/connection.properties");
        config.load();
        Class.forName(config.value("jdbc.connection.driver_class"));
        try (Connection connection = DriverManager.getConnection(
                config.value("jdbc.connection.url"),
                config.value("jdbc.connection.username"),
                config.value("jdbc.connection.password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
