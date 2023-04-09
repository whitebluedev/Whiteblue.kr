package com.whiteblue.backend.domain.user.dto

import jakarta.validation.constraints.NotBlank

data class CreateUserRequest(
    @field:NotBlank
    val username: String,

    @field:NotBlank
    val name: String,

    @field:NotBlank
    val image: String?
)
