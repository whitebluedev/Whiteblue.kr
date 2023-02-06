package com.whiteblue.backend.domain.user;

import com.whiteblue.backend.domain.user.DTO.GetUserResponse;
import com.whiteblue.backend.security.oAuth.OAuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public GetUserResponse findByAuth(OAuthUser oAuthUser) {
        User user = userRepository.findById(oAuthUser.getId())
                                  .orElseThrow();

        return GetUserResponse.builder()
                              .username(user.getUsername())
                              .name(user.getName())
                              .image(user.getImage())
                              .build();
    }
}
