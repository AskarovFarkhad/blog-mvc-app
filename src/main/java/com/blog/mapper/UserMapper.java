package com.blog.mapper;

import com.blog.dto.user.UserDto;
import com.blog.model.User;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {User.class, UserDto.class}, imports = UUID.class)
public interface UserMapper {

    User toUser(UserDto dto);
}