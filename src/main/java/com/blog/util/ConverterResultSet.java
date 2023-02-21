package com.blog.util;

import com.blog.dto.PostDto;
import com.blog.dto.UserDto;

import javax.crypto.ExemptionMechanismException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConverterResultSet {

    public static List<UserDto> convertResultSetToListUserDto(Optional<ResultSet> optionalResultSet) {
        List<UserDto> users = new ArrayList<>();
        try (ResultSet resultSet = optionalResultSet.orElseThrow(ClassNotFoundException::new)) {
            while (resultSet.next()) {
                users.add(new UserDto(
                        java.util.UUID.fromString(resultSet.getString("user_id")),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static UserDto convertSetToUserDto(Optional<ResultSet> optionalResultSet) {
        UserDto userDto = null;
        try (ResultSet resultSet = optionalResultSet.orElseThrow(ClassNotFoundException::new)) {
            while (resultSet.next()) {
                userDto = new UserDto(
                        java.util.UUID.fromString(resultSet.getString("user_id")),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDto;
    }

    public static List<PostDto> convertSetPostToList(Optional<ResultSet> optionalResultSet) {
        List<PostDto> posts = new ArrayList<>();
        try (ResultSet resultSet = optionalResultSet.orElseThrow(ClassNotFoundException::new)) {
            while (resultSet.next()) {
                posts.add(new PostDto(
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static PostDto convertSetToPostDto(Optional<ResultSet> optionalResultSet) {
        PostDto postDto = null;
        try (ResultSet resultSet = optionalResultSet.orElseThrow(ClassNotFoundException::new)) {
            while (resultSet.next()) {
                postDto = new PostDto(
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("created_at").toLocalDateTime()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return postDto;
    }
}