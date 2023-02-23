package com.blog.dto;

import java.time.LocalDateTime;

public class CommentRequestDto {
    private String content;

    private LocalDateTime createdAt;

    public CommentRequestDto() {
    }

    public CommentRequestDto(String content, LocalDateTime createdAt) {
        this.content = content;
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "CommentRequestDto{" +
                "content='" + content + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}