package com.blog.service;

import com.blog.dto.UserDto;
import com.blog.mapper.UserMapper;
import com.blog.model.User;
import com.blog.repository.UserRepository;
import com.blog.util.ConverterSetUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    @Transactional
    public int save(UserDto dto) {
        User user = mapper.toUser(dto);
        return repository.save(user);
    }

    public List<UserDto> getAllUsers() throws SQLException {
        return ConverterSetUsers.covertSetToList(repository.getAll());
    }
}
