package com.whiteblue.backend.security.auth.dto

import jakarta.validation.constraints.NotBlank

data class RefreshTokenRequest(
    @field:NotBlank
    var accessToken: String
)
