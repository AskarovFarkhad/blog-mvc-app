package com.blog.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "userId")
public class User {

    private UUID userId;

    private String userName;

    private String email;

    private String password;

    private boolean isDeleted;

    private boolean isAdmin;
}