package com.whiteblue.backend.security.OAuth;

import com.whiteblue.backend.domain.user.User;
import com.whiteblue.backend.domain.user.UserRepository;
import com.whiteblue.backend.domain.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class OAuthUserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    public OAuthUser loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        Map<String, Object> attributes = super.loadUser(userRequest)
                                              .getAttributes();
        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");
        String username = (String) kakao_account.get("email");
        String name = (String) profile.get("nickname");
        String image = (Boolean) profile.get("is_default_image") ? null : (String) profile.get("profile_image_url");

        Optional<User> userOptional = userRepository.findByUsername(username);
        User user;

        if (userOptional.isPresent()) {
            user = userOptional.get();
            user.setName(name);
            user.setImage(image);
        } else {
            user = userRepository.save(User.builder()
                                           .username(username)
                                           .name(name)
                                           .role(UserRole.USER)
                                           .image(image)
                                           .build());
        }

        return new OAuthUser(user, attributes);
    }

}
