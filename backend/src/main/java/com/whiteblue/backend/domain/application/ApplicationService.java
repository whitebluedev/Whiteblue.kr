package com.whiteblue.backend.domain.application;

import com.whiteblue.backend.domain.application.DTO.ResponseApplicationDTO;
import com.whiteblue.backend.domain.application.DTO.SaveApplicationDTO;
import com.whiteblue.backend.domain.user.User;
import com.whiteblue.backend.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    private final UserRepository userRepository;

    public ResponseApplicationDTO findByUser(User user) {
        Application application = applicationRepository.findByWriter(user)
                                                       .orElseGet(() -> applicationRepository.save(Application.builder()
                                                                                                              .name("")
                                                                                                              .phoneNumber("")
                                                                                                              .major("")
                                                                                                              .introduction("")
                                                                                                              .writer(user)
                                                                                                              .build()));

        return ResponseApplicationDTO.builder()
                                     .name(application.getName())
                                     .phoneNumber(application.getPhoneNumber())
                                     .major(application.getMajor())
                                     .introduction(application.getIntroduction())
                                     .writer(user)
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
                             }, () -> applicationRepository.save(saveApplicationDTO.toEntity()));

        return ResponseApplicationDTO.builder()
                                     .name(name)
                                     .phoneNumber(phoneNumber)
                                     .major(major)
                                     .introduction(introduction)
                                     .writer(user)
                                     .build();
    }

}
