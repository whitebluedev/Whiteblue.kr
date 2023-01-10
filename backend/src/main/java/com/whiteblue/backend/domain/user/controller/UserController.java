package com.whiteblue.backend.domain.user.controller;

import com.whiteblue.backend.domain.user.entity.OAuthUser;
import com.whiteblue.backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/auth")
    public OAuthUser authenticate(@AuthenticationPrincipal OAuthUser oAuthUser) {
        return oAuthUser;
    }
}
