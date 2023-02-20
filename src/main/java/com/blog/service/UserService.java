package com.blog.service;

import com.blog.dto.UserDto;
import com.blog.mapper.UserMapper;
import com.blog.model.User;
import com.blog.dao.UserDao;
import com.blog.util.ConverterSetUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao repository;

    private final UserMapper mapper;

    public int save(UserDto dto) {
        User user = mapper.toUser(dto);
        user.setUserId(UUID.randomUUID());
        return repository.save(user);
    }

    public UserDto getById(UUID userId) {
        ResultSet resultSet = repository.getById(userId);
        return ConverterSetUsers.convertSetToUserDto(resultSet);
    }

    public void update(UUID userId, UserDto dto) {
        UserDto userDto = getById(userId);
        userDto.setUserName(dto.getUserName());
        userDto.setEmail(dto.getEmail());
        userDto.setPassword(dto.getPassword());
        repository.update(mapper.toUser(userDto));
    }

    public void delete(UUID userId) {
        repository.delete(userId);
    }

    public List<UserDto> getAllUsers() {
        return ConverterSetUsers.convertSetToList(repository.getAll());
    }
}
