package com.blog.mapper;

import com.blog.dto.UserDto;
import com.blog.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {User.class, UserDto.class, java.util.UUID.class},
        imports = java.util.UUID.class)
public interface UserMapper {

    //@Mapping(target = "userId", expression = "java(UUID.randomUUID())")
    User toUser(UserDto dto);
}