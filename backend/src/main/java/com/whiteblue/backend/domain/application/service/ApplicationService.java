package com.whiteblue.backend.domain.application.service;

import com.whiteblue.backend.domain.application.repository.ApplicationRepository;
import com.whiteblue.backend.domain.application.DTO.CreateApplicationDTO;
import com.whiteblue.backend.domain.application.entity.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public Application create(CreateApplicationDTO createApplicationDTO) {
        return applicationRepository.save(createApplicationDTO.toEntity());
    }
}
