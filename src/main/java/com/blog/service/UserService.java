package com.blog.service;

import com.blog.dao.UserDao;
import com.blog.dto.user.UserDto;
import com.blog.mapper.UserMapper;
import com.blog.model.User;
import com.blog.util.ConverterResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;

    private final UserMapper mapper;

    private final ConverterResultSet converterResultSet;

    @Autowired
    public UserService(UserDao userDao, UserMapper mapper, ConverterResultSet converterResultSet) {
        this.userDao = userDao;
        this.mapper = mapper;
        this.converterResultSet = converterResultSet;
    }

    public int save(UserDto dto) {
        User user = mapper.toUser(dto);
        return userDao.save(user);
    }

    public UserDto getById(UUID userId) {
        return converterResultSet.convertSetToUserDto(userDao.getById(userId));
    }

    public void update(UUID userId, UserDto dto) {
        UserDto userDto = getById(userId);
        userDto.setUserName(dto.getUserName());
        userDto.setEmail(dto.getEmail());
        userDto.setPassword(dto.getPassword());
        userDao.update(mapper.toUser(userDto));
    }

    public void delete(UUID userId) {
        userDao.delete(userId);
    }

    public List<UserDto> getAllUsers() {
        return converterResultSet.convertResultSetToListUserDto(userDao.getAll());
    }
}