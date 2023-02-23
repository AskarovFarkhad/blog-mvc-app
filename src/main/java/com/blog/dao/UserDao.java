package com.blog.dao;

import com.blog.model.User;
import com.blog.repository.CrudRepository;
import com.blog.util.ConnectToDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserDao implements CrudRepository<User> {

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    @Override
    public Optional<ResultSet> getAll() {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            return Optional.of(connection.createStatement().executeQuery("SELECT * FROM users"));
        } catch (Exception e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<ResultSet> getById(UUID userId) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            PreparedStatement queryGetById = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?");
            queryGetById.setObject(1, userId);
            return Optional.of(queryGetById.executeQuery());
        } catch (SQLException | IndexOutOfBoundsException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public int save(User user) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            PreparedStatement queryCreateUser = connection.prepareStatement(
                    "INSERT INTO users (user_id, username, email, password, is_deleted, is_admin) " +
                            "VALUES (?, ?, ?, ?, ?, ?)");
            queryCreateUser.setObject(1, user.getUserId());
            queryCreateUser.setString(2, user.getUserName());
            queryCreateUser.setString(3, user.getEmail());
            queryCreateUser.setString(4, user.getPassword());
            queryCreateUser.setBoolean(5, user.isDeleted());
            queryCreateUser.setBoolean(6, user.isAdmin());
            return queryCreateUser.executeUpdate();
        } catch (SQLException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(User user) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            PreparedStatement queryUpdateUser = connection
                    .prepareStatement("UPDATE users SET username = ?, email = ?, password = ? WHERE user_id = ?");
            queryUpdateUser.setString(1, user.getUserName());
            queryUpdateUser.setString(2, user.getEmail());
            queryUpdateUser.setString(3, user.getPassword());
            queryUpdateUser.setObject(4, user.getUserId());
            return queryUpdateUser.executeUpdate();
        } catch (SQLException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(UUID userId) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            PreparedStatement queryDeleteById = connection.prepareStatement("DELETE FROM users WHERE user_id = ?");
            queryDeleteById.setObject(1, userId);
            return queryDeleteById.executeUpdate();
        } catch (SQLException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return 0;
        }
    }
}