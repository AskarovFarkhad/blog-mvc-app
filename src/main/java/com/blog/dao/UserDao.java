package com.blog.dao;

import com.blog.model.User;
import com.blog.repository.CrudRepository;
import com.blog.util.ConnectToDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public class UserDao implements CrudRepository<User> {

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    @Override
    public ResultSet getAll() {
        try (Connection connection = ConnectToDataSource.getConnection()) {
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
        try (Connection connection = ConnectToDataSource.getConnection()) {
            PreparedStatement query = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?");
            query.setObject(1, id);
            return query.executeQuery();
        } catch (SQLException | IndexOutOfBoundsException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int save(User user) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
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
        try (Connection connection = ConnectToDataSource.getConnection()) {
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
        try (Connection connection = ConnectToDataSource.getConnection()) {
            return connection
                    .createStatement()
                    .executeUpdate("DELETE FROM users WHERE user_id = '" + id + "'");
        } catch (SQLException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return 0;
        }
    }
}