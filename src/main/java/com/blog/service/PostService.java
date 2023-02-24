package com.blog.service;

import com.blog.dao.PostDao;
import com.blog.dto.post.PostRequestDto;
import com.blog.dto.post.PostResponseDto;
import com.blog.mapper.PostMapper;
import com.blog.model.Post;
import com.blog.util.ConverterResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    private final PostDao postDao;

    private final PostMapper mapper;

    private final ConverterResultSet converterResultSet;

    @Autowired
    public PostService(PostDao postDao,
                       PostMapper mapper,
                       ConverterResultSet converterResultSet) {
        this.postDao = postDao;
        this.mapper = mapper;
        this.converterResultSet = converterResultSet;
    }

    public int save(PostRequestDto postRequestDto) {
        Post post = mapper.toPost(postRequestDto);
        return postDao.save(post);
    }

    public PostResponseDto getById(UUID postId) {
        return converterResultSet.convertSetToPostDto(postDao.getById(postId));
    }

    public void update(UUID postId, PostResponseDto dto) {
        PostResponseDto postResponseDto = getById(postId);
        postResponseDto.setTitle(dto.getTitle());
        postResponseDto.setContent(dto.getContent());
        postDao.update(mapper.toPost(postResponseDto));
    }

    public void delete(UUID postId) {
        postDao.delete(postId);
    }

    public List<PostResponseDto> getAll() {
        return converterResultSet.convertSetPostToList(postDao.getAll());
    }
}