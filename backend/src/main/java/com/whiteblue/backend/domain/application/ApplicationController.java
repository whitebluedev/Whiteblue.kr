package com.whiteblue.backend.domain.application;

import com.whiteblue.backend.domain.application.DTO.ResponseApplicationDTO;
import com.whiteblue.backend.domain.application.DTO.SaveApplicationDTO;
import com.whiteblue.backend.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    public ResponseApplicationDTO findByUser(@AuthenticationPrincipal User user) {
        return applicationService.findByUser(user);
    }

    @PostMapping("/")
    public ResponseApplicationDTO save(@Validated @RequestBody SaveApplicationDTO saveApplicationDTO,
                                       @AuthenticationPrincipal User user) {
        return applicationService.save(saveApplicationDTO, user);
    }
}
