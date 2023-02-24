package com.blog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class ConnectToDataSource {

    private static final Logger log = LoggerFactory.getLogger(ConnectToDataSource.class);

    private static final String URL = "jdbc:postgresql://localhost:5432/blog_db";

    private static final String USERNAME = "postgres";

    private static final String PASSWORD = "admin";


    public static Connection getConnection() throws SQLException {
        return connectionDB().orElseThrow(SQLException::new);
    }

    private static Optional<Connection> connectionDB() {
        try {
            Class.forName("org.postgresql.Driver");
            return Optional.ofNullable(DriverManager.getConnection(URL, USERNAME, PASSWORD));
        } catch (Exception e) {
            log.error("Error connecting to database: " + e.getMessage());
            return Optional.empty();
        }
    }
}