package com.blog.mapper;

import com.blog.dto.UserDto;
import com.blog.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {User.class, UserDto.class})
public interface UserMapper {

    //@Mapping(target = "userId", expression = "java(UUID.randomUUID())")
    User toUser(UserDto dto);

    UserDto toUserDto(User user);
}