package com.blog.mapper;

import com.blog.dto.post.PostRequestDto;
import com.blog.dto.post.PostResponseDto;
import com.blog.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {Post.class, PostRequestDto.class, PostResponseDto.class},
        imports = {UUID.class, LocalDateTime.class})
public interface PostMapper {

    @Mapping(target = "postId", expression = "java(UUID.randomUUID())")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    Post toPost(PostRequestDto postRequestDto);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "author", expression = "java(postResponseDto.getAuthor().getUserId())")
    Post toPost(PostResponseDto postResponseDto);
}