package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) throws SQLException {
        connection.createStatement()
                .execute(String.format("CREATE TABLE IF NOT EXISTS %s()", tableName));
    }

    public void dropTable(String tableName) throws SQLException {
        connection.createStatement()
                .execute(String.format("DROP TABLE IF EXISTS %s", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        connection.createStatement()
                .execute(String.format("ALTER TABLE %s ADD %s %s", tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        connection.createStatement()
                .execute(String.format("ALTER TABLE %s DROP COLUMN %s", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        connection.createStatement()
                .execute(String.format("ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName, newColumnName));
    }

    public String getTableScheme(String tableName) throws Exception {
        return ConnectionDemo.getTableScheme(connection, tableName);
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        TableEditor tableEditor = new TableEditor(config);
        tableEditor.initConnection();
        tableEditor.createTable("book");
        System.out.println(tableEditor.getTableScheme("book"));
        tableEditor.addColumn("book", "author", "VARCHAR(30)");
        tableEditor.addColumn("book", "title", "VARCHAR(30)");
        System.out.println(tableEditor.getTableScheme("book"));
        tableEditor.dropColumn("book", "title");
        System.out.println(tableEditor.getTableScheme("book"));
        tableEditor.renameColumn("book", "author", "writer");
        System.out.println(tableEditor.getTableScheme("book"));
        tableEditor.dropTable("book");
    }
}
