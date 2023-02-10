package com.whiteblue.backend.domain.application

import com.whiteblue.backend.domain.application.dto.GetApplicationResponse
import com.whiteblue.backend.domain.application.dto.SaveApplicationRequest
import com.whiteblue.backend.security.oAuth.OAuthUser
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RequestMapping("/application")
@RestController
class ApplicationController(private val applicationService: ApplicationService) {
    @GetMapping("")
    fun getApplication(@AuthenticationPrincipal oAuthUser: OAuthUser): GetApplicationResponse? =
        applicationService.findByUser(oAuthUser)

    @PostMapping("")
    fun saveApplication(
        @RequestBody @Validated saveApplicationRequest: SaveApplicationRequest,
        @AuthenticationPrincipal oAuthUser: OAuthUser
    ) = applicationService.save(saveApplicationRequest, oAuthUser)
}
