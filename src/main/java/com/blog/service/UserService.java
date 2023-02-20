package com.blog.service;

import com.blog.dto.UserDto;
import com.blog.mapper.UserMapper;
import com.blog.model.User;
import com.blog.repository.UserRepository;
import com.blog.util.ConverterSetUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    public int save(UserDto dto) {
        User user = mapper.toUser(dto);
        user.setUserId(UUID.randomUUID());
        return repository.save(user);
    }

    public List<UserDto> getAllUsers() {
        return ConverterSetUsers.covertSetToList(repository.getAll());
    }
}
