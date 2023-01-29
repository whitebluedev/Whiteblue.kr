package com.whiteblue.backend.domain.user.controller;

import com.whiteblue.backend.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    @GetMapping("/auth")
    public User authenticate(@AuthenticationPrincipal User user) {
        return user;
    }
}
