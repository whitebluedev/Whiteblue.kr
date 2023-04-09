package com.whiteblue.backend.domain.application.dto

import jakarta.validation.constraints.NotBlank

data class CreateApplicationRequest(
    @field:NotBlank
    val name: String,

    @field:NotBlank
    val phoneNumber: String,

    @field:NotBlank
    val major: String,

    @field:NotBlank
    val introduction: String,
)
