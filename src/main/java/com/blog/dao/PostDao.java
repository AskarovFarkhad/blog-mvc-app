package com.blog.dao;

import com.blog.model.Post;
import com.blog.repository.CrudRepository;
import com.blog.util.ConnectDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Slf4j
@Repository
public class PostDao implements CrudRepository<Post> {

    @Override
    public ResultSet getAll() {
        try (Connection connection = ConnectDataSource.getConnection()) {
            return connection
                    .createStatement().executeQuery("SELECT * FROM posts");
        } catch (Exception e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ResultSet getById(UUID id) {
        try (Connection connection = ConnectDataSource.getConnection()) {
            return connection
                    .createStatement()
                    .executeQuery("SELECT * FROM posts WHERE post_id = '" + id + "'");
        } catch (SQLException | IndexOutOfBoundsException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int save(Post post) {
        try (Connection connection = ConnectDataSource.getConnection()) {
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
        try (Connection connection = ConnectDataSource.getConnection()) {
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
    public int delete(UUID id) {
        try (Connection connection = ConnectDataSource.getConnection()) {
            return connection
                    .createStatement()
                    .executeUpdate("DELETE FROM posts WHERE post_id = '" + id + "'");
        } catch (SQLException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return 0;
        }
    }
}