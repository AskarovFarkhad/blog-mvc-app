package com.blog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class ConnectToDataSource {

    private static final Logger log = LoggerFactory.getLogger(ConnectToDataSource.class);

    @Value("${spring.datasource.url}")
    private static String URL;

    @Value("${spring.datasource.username}")
    private static String USERNAME;

    @Value("${spring.datasource.password}")
    private static String PASSWORD;


    public static Connection getConnection() throws SQLException {
        return connectionDB().orElseThrow(SQLException::new);
    }

    private static Optional<Connection> connectionDB() {
        try {
            Class.forName("org.postgresql.Driver");
            return Optional.ofNullable(DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/blog_db",
                    "postgres",
                    "admin"));
        } catch (Exception e) {
            log.error("Error connecting to database: " + e.getMessage());
            return Optional.empty();
        }
    }
}