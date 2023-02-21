package com.blog.util;

import com.blog.dto.PostDto;
import com.blog.dto.UUID;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConverterResultSet {

    public static List<UUID> convertResultSetToListUserDto(ResultSet resultSet) throws SQLException {
        List<UUID> users = new ArrayList<>();
        try {
            while (resultSet.next()) {
                users.add(new UUID(
                        java.util.UUID.fromString(resultSet.getString("user_id")),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password")));
            }
        } finally {
            resultSet.close();
        }
        return users;
    }

    public static UUID convertSetToUserDto(ResultSet resultSet) throws SQLException {
        UUID UUID = null;
        try {
            while (resultSet.next()) {
                UUID = new UUID(
                        java.util.UUID.fromString(resultSet.getString("user_id")),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
            }
        } finally {
            resultSet.close();
        }
        return UUID;
    }

    public static List<PostDto> convertSetPostToList(ResultSet resultSet) throws SQLException {
        List<PostDto> posts = new ArrayList<>();
        try {
            while (resultSet.next()) {
                posts.add(new PostDto(
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        } finally {
            resultSet.close();
        }
        return posts;
    }

    public static PostDto convertSetToPostDto(ResultSet resultSet) throws SQLException {
        PostDto postDto = null;
        try {
            while (resultSet.next()) {
                postDto = new PostDto(
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("created_at").toLocalDateTime()
                );
            }
        } finally {
            resultSet.close();
        }
        return postDto;
    }
}