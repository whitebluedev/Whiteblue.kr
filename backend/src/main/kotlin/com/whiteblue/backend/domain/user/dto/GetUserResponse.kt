package com.whiteblue.backend.domain.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class GetUserResponse(
    @field:NotBlank
    @field:Email
    var username: String,

    @field:NotBlank
    var name: String,

    var image: String?
)
