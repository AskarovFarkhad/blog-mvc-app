package com.blog.util;

import com.blog.dto.UserDto;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class ConverterSetUsers {

    public static List<UserDto> convertSetToList(ResultSet resultSet) {
        List<UserDto> users = new ArrayList<>();
        try {
            while (resultSet.next()) {
                users.add(UserDto.builder()
                        .userId(UUID.fromString(resultSet.getString("user_id")))
                        .userName(resultSet.getString("username"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build());
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return users;
    }

    public static UserDto convertSetToUserDto(ResultSet resultSet) {
        UserDto userDto = null;
        try {
            while (resultSet.next()) {
                userDto = UserDto.builder()
                        .userId(UUID.fromString(resultSet.getString("user_id")))
                        .userName(resultSet.getString("username"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return userDto;
    }
}