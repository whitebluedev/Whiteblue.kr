package com.whiteblue.backend.security.oAuth

import com.whiteblue.backend.config.ApplicationProperties
import com.whiteblue.backend.security.jwt.JWTProvider
import com.whiteblue.backend.util.CookieUtil
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder

@Component
class OAuthAuthenticationSuccessHandler(
    private val applicationProperties: ApplicationProperties,

    private val jwtProvider: JWTProvider
): SimpleUrlAuthenticationSuccessHandler() {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        if (response.isCommitted) return

        val redirectUrl = UriComponentsBuilder.fromUriString(applicationProperties.redirectUrl)
            .queryParam("accessToken", jwtProvider.createAccessToken(authentication))
            .build()
            .toUriString()
        CookieUtil.addCookie(response, "refresh_token", jwtProvider.createRefreshToken(authentication))

        redirectStrategy.sendRedirect(request, response, redirectUrl)
    }
}
