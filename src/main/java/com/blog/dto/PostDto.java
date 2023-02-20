package com.blog.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private UUID postId;

    @NotBlank(message = "\"Title\" field must not be empty")
    private String title;

    @NotBlank(message = "\"Content\" field must not be empty")
    @Size(min = 10, message = "\"Content\" field should be don't less 10 characters long")
    private String content;

    private LocalDateTime createdAt;

    @NotEmpty(message = "\"Author\" field must not be empty")
    private UUID author;
}