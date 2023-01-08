package com.whiteblue.backend.domain.user.service;

import com.whiteblue.backend.domain.user.repository.UserRepository;
import com.whiteblue.backend.domain.user.entity.OAuthUser;
import com.whiteblue.backend.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class OAuthUserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuthUser oAuthUser = new OAuthUser(super.loadUser(userRequest)
                                                 .getAttributes());
        String email = oAuthUser.getEmail();
        String username = oAuthUser.getUsername();

        userRepository.findByEmail(email)
                      .ifPresentOrElse(user -> {
                          user.setEmail(email);
                          user.setUsername(username);
                          userRepository.save(user);
                      }, () -> {
                          User user = User.builder()
                                          .email(email)
                                          .username(username)
                                          .build();
                          userRepository.save(user);
                      });

        return oAuthUser;
    }
}
