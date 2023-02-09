package com.whiteblue.backend.domain.user

import com.whiteblue.backend.domain.user.dto.GetUserResponse
import com.whiteblue.backend.security.oAuth.OAuthUser
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class UserController(private val userService: UserService) {
    @GetMapping("")
    fun getUser(@AuthenticationPrincipal oAuthUser: OAuthUser): GetUserResponse {
        return userService.findByAuth(oAuthUser)
    }
}
