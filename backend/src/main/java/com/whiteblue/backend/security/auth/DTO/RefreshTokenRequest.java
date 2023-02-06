package com.whiteblue.backend.security.auth.DTO;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshTokenRequest {
    @NotBlank
    private String accessToken;

    @Builder
    public RefreshTokenRequest(String accessToken) {
        this.accessToken = accessToken;
    }
}
