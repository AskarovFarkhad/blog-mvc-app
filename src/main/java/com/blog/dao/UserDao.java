package com.blog.dao;

import com.blog.model.User;
import com.blog.repository.CrudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Slf4j
@Repository
public class UserDao implements CrudRepository<User> {

    public Connection connectionDB() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/blog_db",
                    "postgres",
                    "admin");
        } catch (Exception e) {
            log.error("Error connecting to database: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ResultSet getAll() {
        try (Connection connection = connectionDB()) {
            return connection
                    .createStatement()
                    .executeQuery("SELECT * FROM users");
        } catch (Exception e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ResultSet getById(UUID id) {
        try (Connection connection = connectionDB()) {
            return connection
                    .createStatement()
                    .executeQuery("SELECT * FROM users WHERE user_id = '" + id + "'");
        } catch (SQLException | IndexOutOfBoundsException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int save(User user) {
        try (Connection connection = connectionDB()) {
            return connection
                    .createStatement()
                    .executeUpdate("INSERT INTO users (user_id, username, email, password, is_deleted, is_admin)" +
                            " VALUES ('" + user.getUserId() + "', '" + user.getUserName() + "', '" + user.getEmail() +
                            "', '" + user.getPassword() + "', '" + user.isDeleted() + "', '" + user.isAdmin() + "')");
        } catch (SQLException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(User user) {
        try (Connection connection = connectionDB()) {
            return connection
                    .createStatement()
                    .executeUpdate("UPDATE users SET username = '" + user.getUserName() +
                            "', email = '" + user.getEmail() +
                            "', password = '" + user.getPassword() + "' WHERE user_id = '" + user.getUserId() + "'");
        } catch (SQLException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(UUID id) {
        try (Connection connection = connectionDB()) {
            return connection
                    .createStatement()
                    .executeUpdate("DELETE FROM users WHERE user_id = '" + id + "'");
        } catch (SQLException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return 0;
        }
    }
}