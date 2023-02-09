package com.whiteblue.backend.domain.application.dto

import jakarta.validation.constraints.NotBlank
import org.apache.catalina.User

data class SaveApplicationRequest(
    @field:NotBlank
    var name: String,

    @field:NotBlank
    var phoneNumber: String,

    @field:NotBlank
    var major: String,

    @field:NotBlank
    var introduction: String,
) {
    var writer: User? = null
}
