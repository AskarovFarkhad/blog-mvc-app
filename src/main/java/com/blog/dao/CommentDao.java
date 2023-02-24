package com.blog.dao;

import com.blog.model.Comment;
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
public class CommentDao implements CrudRepository<Comment> {

    private static final Logger log = LoggerFactory.getLogger(CommentDao.class);

    @Override
    public Optional<ResultSet> getAll() {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            return Optional.of(connection.createStatement().executeQuery("SELECT * FROM comments"));
        } catch (Exception e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<ResultSet> getById(UUID postId) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            PreparedStatement queryGetById =
                    connection.prepareStatement("SELECT * FROM comments WHERE post_id = ?");
            queryGetById.setObject(1, postId);
            return Optional.of(queryGetById.executeQuery());
        } catch (SQLException | IndexOutOfBoundsException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public int save(Comment comment) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            PreparedStatement queryCreateUser = connection.prepareStatement(
                    "INSERT INTO comments (comment_id, content, created_at, user_id, post_id) VALUES (?, ?, ?, ?, ?)");
            queryCreateUser.setObject(1, comment.getCommentId());
            queryCreateUser.setString(2, comment.getContent());
            queryCreateUser.setObject(3, comment.getCreatedAt());
            queryCreateUser.setObject(4, comment.getUserId());
            queryCreateUser.setObject(5, comment.getPostId());
            return queryCreateUser.executeUpdate();
        } catch (SQLException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(Comment comment) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            PreparedStatement queryUpdateUser = connection
                    .prepareStatement("UPDATE comments SET content = ? WHERE comment_id = ?");
            queryUpdateUser.setString(1, comment.getContent());
            queryUpdateUser.setObject(2, comment.getCommentId());
            return queryUpdateUser.executeUpdate();
        } catch (SQLException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(UUID commentId) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            PreparedStatement queryDeleteById =
                    connection.prepareStatement("DELETE FROM comments WHERE comment_id = ?");
            queryDeleteById.setObject(1, commentId);
            return queryDeleteById.executeUpdate();
        } catch (SQLException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return 0;
        }
    }

    public Optional<ResultSet> getByCommentId(UUID commentId) {
        try (Connection connection = ConnectToDataSource.getConnection()) {
            PreparedStatement queryGetById =
                    connection.prepareStatement("SELECT * FROM comments WHERE comment_id = ?");
            queryGetById.setObject(1, commentId);
            return Optional.of(queryGetById.executeQuery());
        } catch (SQLException | IndexOutOfBoundsException e) {
            log.error("An exception was thrown while working with the database: " + e.getMessage());
            return Optional.empty();
        }
    }
}