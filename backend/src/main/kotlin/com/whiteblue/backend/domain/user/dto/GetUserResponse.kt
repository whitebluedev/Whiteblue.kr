package com.whiteblue.backend.domain.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class GetUserResponse(
    @field:NotBlank
    @field:Email
    val username: String,

    @field:NotBlank
    val name: String,

    val image: String?
)
