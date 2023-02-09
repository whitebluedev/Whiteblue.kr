package com.whiteblue.backend.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding

@ConfigurationPropertiesBinding
@ConfigurationProperties(prefix = "properties")
data class ApplicationProperties(
    val secretKey: String,

    val accessTokenExpire: Int,

    val refreshTokenExpire: Int,

    val redirectUrl: String
)
