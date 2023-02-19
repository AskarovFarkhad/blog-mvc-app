package com.blog.repository;

import com.blog.model.User;
import com.blog.util.ConnectDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Repository
public class UserRepository implements CrudRepository<User> {

    @Override
    public ResultSet getAll() {
        try (Connection connection = ConnectDataSource.getConnection()) {
            return connection
                    .createStatement()
                    .executeQuery("SELECT * FROM users");
        } catch (SQLException e) {
            log.error("Ошибка при получении полного списка: " + e.getCause());
            return null;
        }
    }

    @Override
    public ResultSet getById(int id) {
        try (Connection connection = ConnectDataSource.getConnection()) {
            return connection
                    .createStatement()
                    .executeQuery("SELECT * FROM users WHERE user_id = " + id);
        } catch (SQLException | IndexOutOfBoundsException e) {
            log.error("Ошибка при поиске человека по ID: " + e.getCause());
            return null;
        }
    }

    @Override
    public int save(User user) {
        try (Connection connection = ConnectDataSource.getConnection()) {
            return connection
                    .createStatement()
                    .executeUpdate("INSERT INTO users(user_id, username, email, password, is_deleted, is_admin)" +
                            " VALUES ('" + user.getUserName() + "', '" + user.getEmail() + "', '"
                            + user.getPassword() + "', '" + user.isDeleted() + "', '" + user.isAdmin() + "')");
        } catch (SQLException e) {
            log.error("Ошибка при добавлении человека в СУБД: " + e.getCause());
            return 0;
        }
    }

    @Override
    public int update(User user) {
        try (Connection connection = ConnectDataSource.getConnection()) {
            return connection
                    .createStatement()
                    .executeUpdate("UPDATE users SET " +
                            "username = '" + user.getUserName() +
                            "', email = '" + user.getEmail() +
                            "', password = '" + user.getPassword() +
                            "WHERE user_id = " + user.getUserId());
        } catch (SQLException e) {
            log.error("Ошибка при добавлении человека в СУБД: " + e.getCause());
            return 0;
        }
    }

    @Override
    public int delete(User user) {
        try (Connection connection = ConnectDataSource.getConnection()) {
            return connection
                    .createStatement()
                    .executeUpdate("DELETE FROM users" +
                            " WHERE username = '" + user.getUserName() +
                            "' AND email = '" + user.getEmail() +
                            "' AND password = '" + user.getPassword() + "'");
        } catch (SQLException e) {
            log.error("Ошибка при удалении человека с СУБД: " + e.getCause());
            return 0;
        }
    }

    @Override
    public int deleteById(int id) {
        try (Connection connection = ConnectDataSource.getConnection()) {
            return connection
                    .createStatement()
                    .executeUpdate("DELETE FROM users WHERE user_id = " + id);
        } catch (SQLException | IndexOutOfBoundsException e) {
            log.error("Ошибка при удалении человека с СУБД: " + e.getCause());
            return 0;
        }
    }

    @Override
    public int deleteAll() {
        try (Connection connection = ConnectDataSource.getConnection()) {
            return connection
                    .createStatement()
                    .executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            log.error("Ошибка при очистке СУБД: " + e.getCause());
            return 0;
        }
    }
}