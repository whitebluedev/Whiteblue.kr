package com.whiteblue.backend.domain.user.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class UpdateUserRequest(
    @field:NotBlank
    val name: String,

    @field:NotEmpty
    val image: String?,
)
