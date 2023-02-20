package com.blog.util;

import com.blog.dto.UserDto;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ConverterSetUsers {

    public static List<UserDto> covertSetToList(ResultSet resultSet) {
        List<UserDto> users = new ArrayList<>();
        try {
            while (resultSet.next()) {
                users.add(UserDto.builder()
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
}