package com.blog.dto.tag;


import com.blog.dto.post.PostResponseDto;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public class TagResponseDto {

    private UUID tagId;

    @NotBlank(message = "\"Tag\" field must not be empty")
    private String name;

    private List<PostResponseDto> posts;

    public TagResponseDto() {
    }

    public TagResponseDto(UUID tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }

    public UUID getTagId() {
        return tagId;
    }

    public void setTagId(UUID tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostResponseDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostResponseDto> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "TagItemResponseDto{" +
                "tagId=" + tagId +
                ", name='" + name + '\'' +
                ", posts=" + posts +
                '}';
    }
}