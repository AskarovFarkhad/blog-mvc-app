package com.blog.service;

import com.blog.dao.UserDao;
import com.blog.dto.UUID;
import com.blog.mapper.UserMapper;
import com.blog.model.User;
import com.blog.util.ConverterResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    private final UserDao repository;

    private final UserMapper mapper;

    @Autowired
    public UserService(UserDao repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public int save(UUID dto) {
        User user = mapper.toUser(dto);
        user.setUserId(java.util.UUID.randomUUID());
        return repository.save(user);
    }

    public UUID getById(java.util.UUID userId) throws SQLException {
        ResultSet resultSet = repository.getById(userId);
        return ConverterResultSet.convertSetToUserDto(resultSet);
    }

    public void update(java.util.UUID userId, UUID dto) throws SQLException {
        UUID UUID = getById(userId);
        UUID.setUserName(dto.getUserName());
        UUID.setEmail(dto.getEmail());
        UUID.setPassword(dto.getPassword());
        repository.update(mapper.toUser(UUID));
    }

    public void delete(java.util.UUID userId) {
        repository.delete(userId);
    }

    public List<UUID> getAllUsers() throws SQLException {
        return ConverterResultSet.convertResultSetToListUserDto(repository.getAll());
    }
}