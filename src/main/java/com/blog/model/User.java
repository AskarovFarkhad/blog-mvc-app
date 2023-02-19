package com.blog.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "userId")
public class User {

    private Long userId;

    private String userName;

    private String email;

    private String password;

    private boolean isDeleted;

    private boolean isAdmin;
}