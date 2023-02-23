package com.blog.util;

import com.blog.dao.UserDao;
import com.blog.dto.PostResponseDto;
import com.blog.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ConverterResultSet {

    private static final Logger log = LoggerFactory.getLogger(ConverterResultSet.class);

    private final UserDao userDao;

    @Autowired
    public ConverterResultSet(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<UserDto> convertResultSetToListUserDto(Optional<ResultSet> optionalResultSet) {
        List<UserDto> users = new ArrayList<>();
        try (ResultSet resultSet = optionalResultSet.orElseThrow(ClassNotFoundException::new)) {
            while (resultSet.next()) {
                users.add(new UserDto(
                        UUID.fromString(resultSet.getString("user_id")),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error("An error occurred while converting the parameters of the ResultSet object with databases: {}",
                    e.getMessage());
        }
        return users;
    }

    public UserDto convertSetToUserDto(Optional<ResultSet> optionalResultSet) {
        UserDto userDto = null;
        try (ResultSet resultSet = optionalResultSet.orElseThrow(ClassNotFoundException::new)) {
            while (resultSet.next()) {
                userDto = new UserDto(
                        UUID.fromString(resultSet.getString("user_id")),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error("An error occurred while converting the parameters of the ResultSet object with databases: {}",
                    e.getMessage());
        }
        return userDto;
    }

    public List<PostResponseDto> convertSetPostToList(Optional<ResultSet> optResultSetPost) {
        List<PostResponseDto> posts = new ArrayList<>();
        try (ResultSet resultSet = optResultSetPost.orElseThrow(ClassNotFoundException::new)) {
            while (resultSet.next()) {
                posts.add(new PostResponseDto(
                        UUID.fromString(resultSet.getString("post_id")),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("created_at").toLocalDateTime(),
                        convertSetToUserDto(userDao.getById(UUID.fromString(resultSet.getString("user_id"))))
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error("An error occurred while converting the parameters of the ResultSet object with databases: {}",
                    e.getMessage());
        }
        return posts;
    }

    public PostResponseDto convertSetToPostDto(Optional<ResultSet> optionalResultSet) {
        PostResponseDto postResponseDto = null;
        try (ResultSet resultSet = optionalResultSet.orElseThrow(ClassNotFoundException::new)) {
            while (resultSet.next()) {
                postResponseDto = new PostResponseDto(
                        UUID.fromString(resultSet.getString("post_id")),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("created_at").toLocalDateTime(),
                        convertSetToUserDto(userDao.getById(UUID.fromString(resultSet.getString("user_id"))))
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            log.error("An error occurred while converting the parameters of the ResultSet object with databases: {}",
                    e.getMessage());
        }
        return postResponseDto;
    }
}