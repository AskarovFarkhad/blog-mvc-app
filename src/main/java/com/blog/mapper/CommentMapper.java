package com.blog.mapper;

import com.blog.dto.comment.CommentRequestDto;
import com.blog.dto.comment.CommentResponseDto;
import com.blog.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {Comment.class, CommentRequestDto.class, CommentResponseDto.class},
        imports = {UUID.class, LocalDateTime.class})
public interface CommentMapper {

    @Mapping(target = "commentId", expression = "java(UUID.randomUUID())")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "userId", expression = "java(commentRequestDto.getAuthor())")
    Comment toComment(CommentRequestDto commentRequestDto);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "userId", expression = "java(commentResponseDto.getAuthor().getUserId())")
    Comment toComment(CommentResponseDto commentResponseDto);
}