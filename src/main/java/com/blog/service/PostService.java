package com.blog.service;

import com.blog.dao.PostDao;
import com.blog.dao.UserDao;
import com.blog.dto.PostDto;
import com.blog.mapper.PostMapper;
import com.blog.model.Post;
import com.blog.util.ConverterResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    private final PostDao postDao;

    private final UserDao userDao;

    private final PostMapper mapper;

    @Autowired
    public PostService(PostDao postDao, UserDao userDao, PostMapper mapper) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.mapper = mapper;
    }

    public int save(PostDto dto) {
        Post post = mapper.toPost(dto);
        return postDao.save(post);
    }

    public PostDto getById(UUID postId) throws SQLException {
        ResultSet resultSet = postDao.getById(postId);
        return ConverterResultSet.convertSetToPostDto(resultSet);
    }

    public void update(UUID postId, PostDto dto) {
        // TODO
    }

    public void delete(UUID postId) {
        postDao.delete(postId);
    }

    public List<PostDto> getAll() throws SQLException {
        return ConverterResultSet.convertSetPostToList(postDao.getAll());
    }
}