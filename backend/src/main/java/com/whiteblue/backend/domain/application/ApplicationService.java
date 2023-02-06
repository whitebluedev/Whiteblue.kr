package com.whiteblue.backend.domain.application;

import com.whiteblue.backend.domain.application.DTO.GetApplicationResponse;
import com.whiteblue.backend.domain.application.DTO.SaveApplicationRequest;
import com.whiteblue.backend.domain.user.User;
import com.whiteblue.backend.domain.user.UserRepository;
import com.whiteblue.backend.security.oAuth.OAuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    private final UserRepository userRepository;

    public GetApplicationResponse findByUser(OAuthUser oAuthUser) {
        User user = userRepository.findById(oAuthUser.getId())
                                  .orElseThrow();
        Application application = applicationRepository.findByWriterId(user.getId())
                                                       .orElseGet(() -> applicationRepository.save(Application.builder()
                                                                                                              .name("")
                                                                                                              .phoneNumber("")
                                                                                                              .major("")
                                                                                                              .introduction("")
                                                                                                              .writer(user)
                                                                                                              .build()));

        return GetApplicationResponse.builder()
                                     .name(application.getName())
                                     .phoneNumber(application.getPhoneNumber())
                                     .major(application.getMajor())
                                     .introduction(application.getIntroduction())
                                     .writer(user)
                                     .build();
    }

    public void save(SaveApplicationRequest saveApplicationRequest, OAuthUser oAuthUser) {
        User user = userRepository.findById(oAuthUser.getId())
                                  .orElseThrow();

        Application application = applicationRepository.findByWriterId(oAuthUser.getId())
                                                       .orElse(applicationRepository.save(saveApplicationRequest.toEntity()));
        application.setName(saveApplicationRequest.getName());
        application.setPhoneNumber(saveApplicationRequest.getPhoneNumber());
        application.setMajor(saveApplicationRequest.getMajor());
        application.setIntroduction(saveApplicationRequest.getIntroduction());
    }
}
