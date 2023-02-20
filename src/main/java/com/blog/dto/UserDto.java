package com.blog.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID userId;

    @NotBlank(message = "\"Name\" field must not be empty")
    private String userName;

    @Email(message = "\"Email\" field should be valid")
    @NotBlank(message = "\"Email\" field must not be empty")
    private String email;

    @NotBlank(message = "\"Password\" field must not be empty")
    @Size(min = 8, message = "\"Password\" field should be don't less 8 characters long")
    private String password;
}