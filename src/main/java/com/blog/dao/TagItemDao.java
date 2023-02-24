package com.blog.dao;

import com.blog.model.TagItem;
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
public class TagItemDao implements CrudRepository<TagItem> {

    private static final Logger log = LoggerFactory.getLogger(TagItemDao.class);

    @Override
    public Optional<ResultSet> getAll() {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            return Optional.of(connection.createStatement().executeQuery("SELECT * FROM tags"));
        } catch (Exception e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override // ок
    public Optional<ResultSet> getById(UUID postId) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            PreparedStatement queryGetById = connection.prepareStatement(
                    "SELECT tag_id, name FROM post_tags INNER JOIN tags USING(tag_id) WHERE post_id = ?");
            queryGetById.setObject(1, postId);
            return Optional.of(queryGetById.executeQuery());
        } catch (SQLException | IndexOutOfBoundsException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public int save(TagItem tagItem) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            PreparedStatement queryCreateUser =
                    connection.prepareStatement("INSERT INTO tags (tag_id, name) VALUES (?, ?)");
            queryCreateUser.setObject(1, tagItem.getTagId());
            queryCreateUser.setString(2, tagItem.getName());
            return queryCreateUser.executeUpdate();
        } catch (SQLException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(TagItem tagItem) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            PreparedStatement queryUpdateUser = connection
                    .prepareStatement("UPDATE tags SET name = ? WHERE tag_id = ?");
            queryUpdateUser.setString(1, tagItem.getName());
            queryUpdateUser.setObject(2, tagItem.getTagId());
            return queryUpdateUser.executeUpdate();
        } catch (SQLException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(UUID tagId) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            PreparedStatement queryDeleteById = connection.prepareStatement("DELETE FROM tags WHERE tag_id = ?");
            queryDeleteById.setObject(1, tagId);
            return queryDeleteById.executeUpdate();
        } catch (SQLException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return 0;
        }
    }
}