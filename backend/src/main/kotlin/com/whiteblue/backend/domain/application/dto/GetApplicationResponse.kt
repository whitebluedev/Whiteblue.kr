package com.whiteblue.backend.domain.application.dto

import com.whiteblue.backend.domain.user.entity.User
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class GetApplicationResponse(
    @field:NotBlank
    val name: String,

    @field:NotBlank
    val phoneNumber: String,

    @field:NotBlank
    val major: String,

    @field:NotBlank
    val introduction: String,

    @field:NotNull
    val writer: User,
)
