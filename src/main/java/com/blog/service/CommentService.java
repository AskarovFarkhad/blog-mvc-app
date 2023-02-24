package com.blog.service;

import com.blog.dao.CommentDao;
import com.blog.dto.comment.CommentRequestDto;
import com.blog.dto.comment.CommentResponseDto;
import com.blog.mapper.CommentMapper;
import com.blog.model.Comment;
import com.blog.util.ConverterResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public int save(CommentRequestDto commentRequestDto, UUID postId) {
        Comment comment = mapper.toComment(commentRequestDto);
        comment.setPostId(postId);
        return commentDao.save(comment);
    }

    public CommentResponseDto getByCommentId(UUID commentId) {
        return converterResultSet.convertSetToCommentDto(commentDao.getByCommentId(commentId));
    }

    public void update(UUID commentId, CommentResponseDto dto) {
        CommentResponseDto commentResponseDto = getByCommentId(commentId);
        commentResponseDto.setContent(dto.getContent());
        commentDao.update(mapper.toComment(commentResponseDto));
    }

    public void delete(UUID commentId) {
        commentDao.delete(commentId);
    }
}