package com.whiteblue.backend.global.auth.dto

import jakarta.validation.constraints.NotBlank

data class RefreshTokenRequest(
    @field:NotBlank
    val accessToken: String
)
