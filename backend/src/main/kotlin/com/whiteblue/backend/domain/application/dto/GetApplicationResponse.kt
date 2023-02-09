package com.whiteblue.backend.domain.application.dto

import com.whiteblue.backend.domain.user.User
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class GetApplicationResponse(
    @field:NotBlank
    var name: String,

    @field:NotBlank
    var phoneNumber: String,

    @field:NotBlank
    var major: String,

    @field:NotBlank
    var introduction: String,

    @field:NotNull
    var writer: User,
)
