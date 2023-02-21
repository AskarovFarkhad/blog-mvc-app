package com.blog.util;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Component
@NoArgsConstructor
public class ConnectDataSource {

    public Connection getConnection() throws SQLException {
        return connectionDB().orElseThrow(SQLException::new);
    }

    private Optional<Connection> connectionDB() {
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

    private Properties initProperties() throws IOException {
        Properties dbProperties = new Properties();
        String rootPath =
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        dbProperties.load(Files.newInputStream(Paths.get(rootPath + "application.properties")));
        return dbProperties;
    }
}