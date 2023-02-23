package com.blog.service;

import com.blog.dao.CommentDao;
import com.blog.dto.CommentRequestDto;
import com.blog.dto.CommentResponseDto;
import com.blog.mapper.CommentMapper;
import com.blog.model.Comment;
import com.blog.util.ConverterResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    private final CommentDao commentDao;

    private final CommentMapper mapper;

    private final ConverterResultSet converterResultSet;

    @Autowired
    public CommentService(CommentDao commentDao, CommentMapper mapper, ConverterResultSet converterResultSet) {
        this.commentDao = commentDao;
        this.mapper = mapper;
        this.converterResultSet = converterResultSet;
    }

    public int save(CommentRequestDto commentRequestDto) {
        Comment comment = mapper.toComment(commentRequestDto);
        return commentDao.save(comment);
    }

    public CommentResponseDto getById(UUID postId) {
        return converterResultSet.convertSetToCommentDto(commentDao.getById(postId));
    }

    public void update(UUID commentId, CommentRequestDto dto) {
        CommentResponseDto commentResponseDto = getById(commentId);
        commentResponseDto.setContent(dto.getContent());
        commentDao.update(mapper.toComment(commentResponseDto));
    }

    public void delete(UUID commentId) {
        commentDao.delete(commentId);
    }

    public List<CommentResponseDto> getAll() {
        return converterResultSet.convertSetCommentToList(commentDao.getAll());
    }
}