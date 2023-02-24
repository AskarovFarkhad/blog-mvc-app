package com.blog.dto.tag;


import com.blog.dto.post.PostRequestDto;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class TagRequestDto {

    @NotBlank(message = "\"Tag\" field must not be empty")
    private String name;

    private List<PostRequestDto> posts;

    public TagRequestDto() {
    }

    public TagRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostRequestDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostRequestDto> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "TagItemRequestDto{" +
                "name='" + name + '\'' +
                ", posts=" + posts +
                '}';
    }
}