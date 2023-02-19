package com.blog.mapper;

import com.blog.dto.UserDto;
import com.blog.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {User.class, UserDto.class})
public interface UserMapper {

    User toUser(UserDto dto);

    UserDto toUserDto(User user);
}
