package com.whiteblue.backend.domain.user.DTO;

import com.whiteblue.backend.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateUserDTO {
    @NotBlank
    @Size(min = 8, max = 12)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 12)
    private String password;

    @Builder
    public CreateUserDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                   .username(username)
                   .email(email)
                   .password(password)
                   .build();
    }
}
