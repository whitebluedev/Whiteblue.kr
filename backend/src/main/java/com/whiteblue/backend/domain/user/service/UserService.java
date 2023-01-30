package com.whiteblue.backend.domain.user.service;

import com.whiteblue.backend.domain.user.entity.User;
import com.whiteblue.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        User user = new User(super.loadUser(userRequest)
                                  .getAttributes());

        String email = user.getEmail();
        String username = user.getUsername();

        userRepository.findByEmail(email)
                      .ifPresentOrElse(u -> {
                          u.setEmail(email);
                          u.setUsername(username);
                          userRepository.save(u);
                      }, () -> userRepository.save(user));

        return userRepository.findByEmail(email)
                             .orElseThrow();
    }
}
