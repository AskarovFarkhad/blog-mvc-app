package com.blog.mapper;

import com.blog.dto.user.UserDto;
import com.blog.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {User.class, UserDto.class}, imports = UUID.class)
public interface UserMapper {

    @Mapping(target = "userId", expression = "java(UUID.randomUUID())")
    User toUser(UserDto dto);
}