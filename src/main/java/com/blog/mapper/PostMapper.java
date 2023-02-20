package com.blog.mapper;

import com.blog.dto.PostDto;
import com.blog.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {Post.class, PostDto.class},
        imports = {UUID.class, LocalDateTime.class})
public interface PostMapper {

    @Mapping(target = "postId", expression = "java(UUID.randomUUID())")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    Post toPost(PostDto dto);

    PostDto toPostDto(Post post);
}