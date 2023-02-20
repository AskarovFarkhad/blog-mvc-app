package com.blog.service;

import com.blog.dao.PostDao;
import com.blog.dao.UserDao;
import com.blog.dto.PostDto;
import com.blog.mapper.PostMapper;
import com.blog.model.Post;
import com.blog.util.ConverterSetUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostDao postDao;

    private final UserDao userDao;

    private final PostMapper mapper;

    public int save(PostDto dto) {
        Post post = mapper.toPost(dto);
        return postDao.save(post);
    }

    public PostDto getById(UUID postId) {
        ResultSet resultSet = postDao.getById(postId);
        return ConverterSetUsers.convertSetToPostDto(resultSet);
    }

    public void update(UUID postId, PostDto dto) {
        // TODO
    }

    public void delete(UUID postId) {
        postDao.delete(postId);
    }

    public List<PostDto> getAll() {
        return ConverterSetUsers.convertSetPostToList(postDao.getAll());
    }
}