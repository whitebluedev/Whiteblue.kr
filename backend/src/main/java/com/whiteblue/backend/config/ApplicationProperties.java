package com.whiteblue.backend.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "properties")
public class ApplicationProperties {
    @NotBlank
    private final String secretKey;

    @NotNull
    private final Integer accessTokenExpire;

    @NotNull
    private final Integer refreshTokenExpire;

    @NotBlank
    private final String redirectUrl;

}
