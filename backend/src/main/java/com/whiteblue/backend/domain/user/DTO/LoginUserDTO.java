package com.whiteblue.backend.domain.user.DTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginUserDTO {
    @NotBlank
    @Size(min = 8, max = 12)
    private String username;

    @NotBlank
    @Size(min = 8, max = 12)
    private String password;

    @Builder
    public LoginUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
