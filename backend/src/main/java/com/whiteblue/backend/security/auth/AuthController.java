package com.whiteblue.backend.security.auth;

import com.whiteblue.backend.security.auth.DTO.RefreshTokenRequest;
import com.whiteblue.backend.security.auth.DTO.RefreshTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    public final AuthService authService;

    @PostMapping("/refresh")
    public RefreshTokenResponse refreshToken(HttpServletRequest request, @RequestBody @Validated RefreshTokenRequest refreshTokenRequest) {
        return authService.refresh(request, refreshTokenRequest.getAccessToken());
    }
}
