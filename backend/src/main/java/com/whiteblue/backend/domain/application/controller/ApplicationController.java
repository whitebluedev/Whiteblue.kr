package com.whiteblue.backend.domain.application.controller;

import com.whiteblue.backend.domain.application.service.ApplicationService;
import com.whiteblue.backend.domain.application.DTO.CreateApplicationDTO;
import com.whiteblue.backend.domain.application.entity.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/application")
@RestController
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/")
    public Application write(@Validated @RequestBody CreateApplicationDTO createApplicationDTO,
                             @AuthenticationPrincipal OAuth2User user) {
        return null;
    }
}
