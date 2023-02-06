package com.whiteblue.backend.domain.user;

import com.whiteblue.backend.domain.user.DTO.GetUserResponse;
import com.whiteblue.backend.security.oAuth.OAuthUser;
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

    @GetMapping("")
    public GetUserResponse getUser(@AuthenticationPrincipal OAuthUser oAuthUser) {
        return userService.findByAuth(oAuthUser);
    }
}
