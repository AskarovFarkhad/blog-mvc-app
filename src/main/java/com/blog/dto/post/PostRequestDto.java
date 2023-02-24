package com.blog.dto.post;

import com.blog.dto.tag.TagItemRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public class PostRequestDto {

    @NotBlank(message = "\"Title\" field must not be empty")
    private String title;

    @NotBlank(message = "\"Content\" field must not be empty")
    @Size(min = 10, message = "\"Content\" field should be don't less 10 characters long")
    private String content;

    private UUID author;

    private List<TagItemRequestDto> tags;

    public PostRequestDto() {
    }

    public PostRequestDto(String title, String content, UUID author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UUID getAuthor() {
        return author;
    }

    public void setAuthor(UUID author) {
        this.author = author;
    }

    public List<TagItemRequestDto> getTags() {
        return tags;
    }

    public void setTags(List<TagItemRequestDto> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "PostRequestDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author=" + author +
                '}';
    }
}