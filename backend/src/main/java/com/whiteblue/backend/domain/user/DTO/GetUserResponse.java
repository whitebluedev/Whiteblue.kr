package com.whiteblue.backend.domain.user.DTO;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetUserResponse {
    @NotBlank
    @Email
    private String username;

    @NotBlank
    private String name;

    private String image;

    @Builder
    public GetUserResponse(String username, String name, String image) {
        this.username = username;
        this.name = name;
        this.image = image;
    }
}
