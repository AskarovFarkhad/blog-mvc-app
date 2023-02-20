package com.blog.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "commentId")
public class Comment {

    private UUID commentId;

    private String content;

    private LocalDateTime createdAt;

    private UUID userId;

    private UUID postId;
}