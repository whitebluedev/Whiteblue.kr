package com.whiteblue.backend.global.config

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.validation.annotation.Validated

@Validated
@ConfigurationPropertiesBinding
@ConfigurationProperties(prefix = "properties")
data class ApplicationProperties(
    @field:NotBlank
    val secretKey: String,

    @field:NotNull
    val accessTokenExpire: Int,

    @field:NotNull
    val refreshTokenExpire: Int,

    @field:NotBlank
    val authorizedUri: String
)
