package com.blog.dao;

import com.blog.model.Post;
import com.blog.repository.CrudRepository;
import com.blog.util.ConnectToDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PostDao implements CrudRepository<Post> {

    private static final Logger log = LoggerFactory.getLogger(PostDao.class);

    @Override
    public Optional<ResultSet> getAll() {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            return Optional.of(connection.createStatement().executeQuery("SELECT * FROM posts"));
        } catch (Exception e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<ResultSet> getById(UUID postId) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            PreparedStatement queryGetById = connection.prepareStatement("SELECT * FROM posts WHERE post_id = ?");
            queryGetById.setObject(1, postId);
            return Optional.of(queryGetById.executeQuery());
        } catch (SQLException | IndexOutOfBoundsException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public int save(Post post) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            return connection
                    .createStatement()
                    .executeUpdate("INSERT INTO posts (post_id, title, content, created_at, user_id)" +
                            " VALUES ('" + post.getPostId() + "', '" + post.getTitle() + "', '" + post.getContent() +
                            "', '" + post.getCreatedAt() + "', '" + post.getAuthor() + "')");
        } catch (SQLException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(Post post) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            return connection
                    .createStatement()
                    .executeUpdate("UPDATE posts SET title = '" + post.getTitle() +
                            "', content = '" + post.getContent()  + "' WHERE user_id = '" + post.getPostId() + "'");
        } catch (SQLException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(UUID postId) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            PreparedStatement queryDeleteById = connection.prepareStatement("DELETE FROM posts WHERE post_id = ?");
            queryDeleteById.setObject(1, postId);
            return queryDeleteById.executeUpdate();
        } catch (SQLException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return 0;
        }
    }
}