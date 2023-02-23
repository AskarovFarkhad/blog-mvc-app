package com.blog.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class CommentRequestDto {

    @NotBlank(message = "\"Content\" field must not be empty")
    private String content;

    private PostResponseDto author;

    public CommentRequestDto() {
    }

    public CommentRequestDto(String content, LocalDateTime createdAt) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentRequestDto{" +
                "content='" + content + '\'' +
                '}';
    }
}