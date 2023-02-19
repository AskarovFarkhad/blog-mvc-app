package com.blog.util;

import com.blog.dto.UserDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConverterSetUsers {

    public static List<UserDto> covertSetToList(ResultSet resultSet) throws SQLException {
        List<UserDto> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(UserDto.builder()
                    .userName(resultSet.getString("username"))
                    .email(resultSet.getString("email"))
                    .password(resultSet.getString("password"))
                    .build());
        }
        return users;
    }
}