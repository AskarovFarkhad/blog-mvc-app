package com.blog.dto.comment;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class CommentRequestDto {

    @NotBlank(message = "\"Content\" field must not be empty")
    private String content;

    private UUID author;

    public CommentRequestDto() {
    }

    public CommentRequestDto(String content, UUID author) {
        this.content = content;
        this.author = author;
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

    @Override
    public String toString() {
        return "CommentRequestDto{" +
                "content='" + content + '\'' +
                ", author=" + author +
                '}';
    }
}