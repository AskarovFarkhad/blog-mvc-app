package com.blog.mapper;

import com.blog.dto.CommentRequestDto;
import com.blog.dto.CommentResponseDto;
import com.blog.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {Comment.class, CommentRequestDto.class},
        imports = {UUID.class, LocalDateTime.class})
public interface CommentMapper {

    @Mapping(target = "commentId", expression = "java(UUID.randomUUID())")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    Comment toComment(CommentRequestDto commentRequestDto);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "userId", expression = "java(commentResponseDto.getUserId().getUserId())")
    @Mapping(target = "postId", expression = "java(commentResponseDto.getPostId().getPostId())")
    Comment toComment(CommentResponseDto commentResponseDto);
}