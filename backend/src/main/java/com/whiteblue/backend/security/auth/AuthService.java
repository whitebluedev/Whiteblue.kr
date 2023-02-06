package com.whiteblue.backend.security.auth;

import com.whiteblue.backend.domain.user.UserRepository;
import com.whiteblue.backend.security.auth.DTO.RefreshTokenResponse;
import com.whiteblue.backend.security.exception.BadRequestException;
import com.whiteblue.backend.security.jwt.JWTProvider;
import com.whiteblue.backend.security.oAuth.OAuthUser;
import com.whiteblue.backend.security.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthService {
    private final UserRepository userRepository;

    private final JWTProvider jwtProvider;

    public RefreshTokenResponse refresh(HttpServletRequest request, String accessToken) {
        String refreshToken = CookieUtil.getCookie(request, "refresh_token")
                                        .map(Cookie::getValue)
                                        .orElseThrow(() -> new BadRequestException("Refresh Token이 존재하지 않습니다."));

        if (!jwtProvider.validateToken(refreshToken)) {
            throw new RuntimeException("유효하지 않은 Refresh Token입니다.");
        }

        Authentication authentication = jwtProvider.getAuthentication(accessToken);
        OAuthUser oAuthUser = (OAuthUser) authentication.getPrincipal();

        if (!userRepository.getRefreshTokenById(oAuthUser.getId())
                           .equals(refreshToken)) {
            throw new RuntimeException("Refresh Token이 일치하지 않습니다.");
        }

        accessToken = jwtProvider.createAccessToken(authentication);

        return RefreshTokenResponse.builder()
                                   .accessToken(accessToken)
                                   .build();
    }
}
