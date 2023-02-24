package com.blog.dto.post;

import com.blog.dto.tag.TagResponseDto;
import com.blog.dto.user.UserDto;
import com.blog.dto.comment.CommentResponseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PostResponseDto {

    private UUID postId;

    @NotBlank(message = "\"Title\" field must not be empty")
    private String title;

    @NotBlank(message = "\"Content\" field must not be empty")
    @Size(min = 10, message = "\"Content\" field should be don't less 10 characters long")
    private String content;

    private LocalDateTime createdAt;

    private UserDto author;

    private List<CommentResponseDto> comments;

    private List<TagResponseDto> tags;

    public PostResponseDto() {
    }

    public PostResponseDto(UUID postId, String title, String content, LocalDateTime createdAt, UserDto author,
                           List<CommentResponseDto> comments, List<TagResponseDto> tags) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
        this.comments = comments;
        this.tags = tags;
    }

    public UUID getPostId() {
        return postId;
    }

    public void setPostId(UUID postId) {
        this.postId = postId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserDto getAuthor() {
        return author;
    }

    public void setAuthor(UserDto author) {
        this.author = author;
    }

    public List<CommentResponseDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponseDto> comments) {
        this.comments = comments;
    }

    public List<TagResponseDto> getTags() {
        return tags;
    }

    public void setTags(List<TagResponseDto> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostResponseDto postResponseDto = (PostResponseDto) o;
        return postId.equals(postResponseDto.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }

    @Override
    public String toString() {
        return "PostResponseDto{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", author=" + author +
                ", comments=" + comments +
                ", tags=" + tags +
                '}';
    }
}