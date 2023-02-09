package com.whiteblue.backend.security.auth

import com.whiteblue.backend.security.auth.dto.RefreshTokenRequest
import com.whiteblue.backend.security.auth.dto.RefreshTokenResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth")
@RestController
class AuthController(private val authService: AuthService) {
    @PostMapping("/refresh")
    fun refreshToken(
        request: HttpServletRequest,
        @RequestBody @Validated refreshTokenRequest: RefreshTokenRequest
    ): RefreshTokenResponse? {
        return authService.refresh(request, refreshTokenRequest.accessToken)
    }
}
