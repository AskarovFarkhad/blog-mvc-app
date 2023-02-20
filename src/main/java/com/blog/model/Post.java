package com.blog.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "postId")
public class Post {

    private UUID postId;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private UUID userId;
}