package com.whiteblue.backend.domain.application;

import com.whiteblue.backend.domain.application.DTO.GetApplicationResponse;
import com.whiteblue.backend.domain.application.DTO.SaveApplicationRequest;
import com.whiteblue.backend.security.oAuth.OAuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/application")
@RestController
public class ApplicationController {
    private final ApplicationService applicationService;

    @GetMapping("")
    public GetApplicationResponse getApplication(@AuthenticationPrincipal OAuthUser oAuthUser) {
        return applicationService.findByUser(oAuthUser);
    }

    @PostMapping("")
    public void saveApplication(@RequestBody @Validated SaveApplicationRequest saveApplicationRequest,
                                @AuthenticationPrincipal OAuthUser oAuthUser) {
        applicationService.save(saveApplicationRequest, oAuthUser);
    }
}
