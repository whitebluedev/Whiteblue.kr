package com.whiteblue.backend.domain.application.service;

import com.whiteblue.backend.domain.application.DTO.ResponseApplicationDTO;
import com.whiteblue.backend.domain.application.DTO.SaveApplicationDTO;
import com.whiteblue.backend.domain.application.entity.Application;
import com.whiteblue.backend.domain.application.repository.ApplicationRepository;
import com.whiteblue.backend.domain.user.entity.User;
import com.whiteblue.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    private final UserRepository userRepository;

    public ResponseApplicationDTO findByUser(User user) {
        Application application = applicationRepository.findByWriter(user)
                                                       .orElse(Application.builder()
                                                                          .name("")
                                                                          .phoneNumber("")
                                                                          .major("")
                                                                          .introduction("")
                                                                          .build());

        return ResponseApplicationDTO.builder()
                                     .name(application.getName())
                                     .phoneNumber(application.getPhoneNumber())
                                     .major(application.getMajor())
                                     .introduction(application.getIntroduction())
                                     .build();
    }

    public ResponseApplicationDTO save(SaveApplicationDTO saveApplicationDTO, User user) {
        String name = saveApplicationDTO.getName();
        String phoneNumber = saveApplicationDTO.getPhoneNumber();
        String major = saveApplicationDTO.getMajor();
        String introduction = saveApplicationDTO.getIntroduction();

        applicationRepository.findByWriter(user)
                             .ifPresentOrElse(application -> {
                                                  application.setName(name);
                                                  application.setPhoneNumber(phoneNumber);
                                                  application.setMajor(major);
                                                  application.setIntroduction(introduction);
                                                  applicationRepository.save(application);
                                              },
                                              () -> applicationRepository.save(Application.builder()
                                                                                          .name(name)
                                                                                          .phoneNumber(phoneNumber)
                                                                                          .major(major)
                                                                                          .introduction(introduction)
                                                                                          .build()));
        return ResponseApplicationDTO.builder()
                                     .name(name)
                                     .phoneNumber(phoneNumber)
                                     .major(major)
                                     .introduction(introduction)
                                     .build();
    }

}
