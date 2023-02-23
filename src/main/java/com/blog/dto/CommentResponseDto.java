package com.blog.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public class CommentResponseDto {

    private UUID commentId;

    @NotBlank(message = "\"Content\" field must not be empty")
    private String content;

    private LocalDateTime createdAt;

    private UserDto userId;

    private PostResponseDto postId;

    public CommentResponseDto() {
    }

    public CommentResponseDto(UUID commentId, String content, LocalDateTime createdAt, UserDto userId, PostResponseDto postId) {
        this.commentId = commentId;
        this.content = content;
        this.createdAt = createdAt;
        this.userId = userId;
        this.postId = postId;
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

    public UserDto getUserId() {
        return userId;
    }

    public void setUserId(UserDto userId) {
        this.userId = userId;
    }

    public PostResponseDto getPostId() {
        return postId;
    }

    public void setPostId(PostResponseDto postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "CommentResponseDto{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", userId=" + userId +
                ", postId=" + postId +
                '}';
    }
}