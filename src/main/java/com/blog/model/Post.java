package com.blog.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "postId")
public class Post {

    private Long postId;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private Long userId;
}