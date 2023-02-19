package com.blog.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "commentId")
public class Comment {

    private Long commentId;

    private String content;

    private LocalDateTime createdAt;

    private Long userId;

    private Long postId;
}