package com.blog.dto.comment;

import com.blog.dto.UserDto;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public class CommentResponseDto {

    private UUID commentId;

    @NotBlank(message = "\"Content\" field must not be empty")
    private String content;

    private LocalDateTime createdAt;

    private UserDto author;

    public CommentResponseDto() {
    }

    public CommentResponseDto(UUID commentId, String content, LocalDateTime createdAt, UserDto author) {
        this.commentId = commentId;
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
    }

    public UUID getCommentId() {
        return commentId;
    }

    public void setCommentId(UUID commentId) {
        this.commentId = commentId;
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

    public UserDto getAuthor() {
        return author;
    }

    public void setAuthor(UserDto userId) {
        this.author = userId;
    }

    @Override
    public String toString() {
        return "CommentResponseDto{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", userId=" + author +
                '}';
    }
}