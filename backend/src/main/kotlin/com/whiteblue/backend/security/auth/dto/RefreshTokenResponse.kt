package com.whiteblue.backend.security.auth.dto

import jakarta.validation.constraints.NotBlank

data class RefreshTokenResponse(
    @field:NotBlank
    var accessToken: String
)
