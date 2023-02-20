package com.blog.util;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.Driver;
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

    public static Connection getConnection() throws SQLException {
        return connectionDB().orElseThrow(SQLException::new);
    }

    public static Optional<Connection> connectionDB() {
        try {
            Properties dbProperties = initProperties();
            DriverManager.registerDriver(new Driver());
            return Optional.ofNullable(DriverManager.getConnection(
                    dbProperties.getProperty("spring.datasource.url"),
                    dbProperties.getProperty("spring.datasource.username"),
                    dbProperties.getProperty("spring.datasource.password")));
        } catch (Exception e) {
            log.error("Error connecting to database: " + e.getMessage());
            return Optional.empty();
        }
    }

    public static Properties initProperties() throws IOException {
        Properties dbProperties = new Properties();
        String rootPath =
                Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        dbProperties.load(Files.newInputStream(Paths.get(rootPath + "application.properties")));
        return dbProperties;
    }
}