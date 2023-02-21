package com.blog.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Comment {

    private UUID commentId;

    private String content;

    private LocalDateTime createdAt;

    private UUID userId;

    private UUID postId;

    public Comment() {
    }

    public Comment(String content, LocalDateTime createdAt, UUID userId, UUID postId) {
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId.equals(comment.commentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", userId=" + userId +
                ", postId=" + postId +
                '}';
    }
}