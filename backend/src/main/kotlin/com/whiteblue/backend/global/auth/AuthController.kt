package com.whiteblue.backend.global.auth

import com.whiteblue.backend.global.auth.dto.RefreshTokenRequest
import com.whiteblue.backend.global.auth.dto.RefreshTokenResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
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
    ): ResponseEntity<RefreshTokenResponse?> =
        ResponseEntity.ok(
            authService.refreshToken(request, refreshTokenRequest.accessToken)
        )
}
