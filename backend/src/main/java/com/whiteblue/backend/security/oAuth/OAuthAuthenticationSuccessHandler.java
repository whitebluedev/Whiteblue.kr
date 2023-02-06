package com.whiteblue.backend.security.oAuth;

import com.whiteblue.backend.config.ApplicationProperties;
import com.whiteblue.backend.security.jwt.JWTProvider;
import com.whiteblue.backend.security.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
@Component
public class OAuthAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final ApplicationProperties applicationProperties;

    private final JWTProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        if (response.isCommitted()) {
            log.debug("응답이 이미 커밋되었습니다.");
            return;
        }
        String redirectUrl = UriComponentsBuilder.fromUriString(applicationProperties.getRedirectUrl())
                                                 .queryParam("accessToken", jwtProvider.createAccessToken(authentication))
                                                 .build()
                                                 .toUriString();
        CookieUtil.addCookie(response, "refresh_token", jwtProvider.createRefreshToken(authentication));
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
