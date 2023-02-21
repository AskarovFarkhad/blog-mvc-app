package com.blog.service;

import com.blog.dao.UserDao;
import com.blog.dto.UserDto;
import com.blog.mapper.UserMapper;
import com.blog.model.User;
import com.blog.util.ConverterResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao repository;

    private final UserMapper mapper;

    @Autowired
    public UserService(UserDao repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public int save(UserDto dto) {
        User user = mapper.toUser(dto);
        user.setUserId(java.util.UUID.randomUUID());
        return repository.save(user);
    }

    public UserDto getById(UUID userId) {
        return ConverterResultSet.convertSetToUserDto(repository.getById(userId));
    }

    public void update(java.util.UUID userId, UserDto dto) {
        UserDto UserDto = getById(userId);
        UserDto.setUserName(dto.getUserName());
        UserDto.setEmail(dto.getEmail());
        UserDto.setPassword(dto.getPassword());
        repository.update(mapper.toUser(UserDto));
    }

    public void delete(java.util.UUID userId) {
        repository.delete(userId);
    }

    public List<UserDto> getAllUsers() {
        return ConverterResultSet.convertResultSetToListUserDto(repository.getAll());
    }
}