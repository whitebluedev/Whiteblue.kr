package com.whiteblue.backend.domain.application.controller

import com.whiteblue.backend.domain.application.dto.ApplicationResponse
import com.whiteblue.backend.domain.application.dto.CreateApplicationRequest
import com.whiteblue.backend.domain.application.dto.UpdateApplicationRequest
import com.whiteblue.backend.domain.application.service.ApplicationServiceImpl
import com.whiteblue.backend.global.oAuth.OAuthUser
import jakarta.validation.constraints.Positive
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RequestMapping("/application")
@RestController
class ApplicationController(private val applicationService: ApplicationServiceImpl) {
    @GetMapping("")
    fun getMyApplication(@AuthenticationPrincipal oAuthUser: OAuthUser): ResponseEntity<ApplicationResponse?> =
        ResponseEntity.ok(
            applicationService.getApplicationByWriterId(oAuthUser.id)
        )

    @PostMapping("")
    fun createApplication(
        @AuthenticationPrincipal oAuthUser: OAuthUser,
        @RequestBody @Validated createApplicationRequest: CreateApplicationRequest,
    ): ResponseEntity<ApplicationResponse> =
        ResponseEntity.ok(
            applicationService.createApplication(oAuthUser.id, createApplicationRequest)
        )

    @PutMapping("/{id}")
    fun updateApplication(
        @PathVariable @Positive id: Long,
        @RequestBody @Validated updateApplicationRequest: UpdateApplicationRequest
    ): ResponseEntity<ApplicationResponse> =
        ResponseEntity.ok(
            applicationService.updateApplication(id, updateApplicationRequest)
        )

    @DeleteMapping("/{id}")
    fun deleteApplication(@PathVariable @Positive id: Long): ResponseEntity<Unit> =
        ResponseEntity.ok(
            applicationService.deleteApplication(id)
        )
}
