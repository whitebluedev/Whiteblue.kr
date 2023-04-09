package com.whiteblue.backend.global.oAuth

import com.whiteblue.backend.global.config.ApplicationProperties
import com.whiteblue.backend.global.jwt.JWTProvider
import com.whiteblue.backend.global.util.CookieUtil
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@Component
class OAuthAuthenticationSuccessHandler(
    private val applicationProperties: ApplicationProperties,

    private val jwtProvider: JWTProvider
) : SimpleUrlAuthenticationSuccessHandler() {
    private val authorizedUri = URI.create(applicationProperties.authorizedUri)

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        if (response.isCommitted) return

        val redirectUri = CookieUtil.getCookie(request, "redirect_uri")?.value
            ?.let {
                if (isAuthorizedUri(it)) {
                    UriComponentsBuilder.fromUriString(it)
                        .queryParam("accessToken", jwtProvider.createAccessToken(authentication))
                        .build()
                        .toUriString()
                } else null
            } ?: throw RuntimeException("적절하지 않은 Redirect URI입니다.")

        CookieUtil.addCookie(response, "refresh_token", jwtProvider.createRefreshToken(authentication))
        CookieUtil.deleteCookie(request, response, "redirect_uri")
        clearAuthenticationAttributes(request)
        redirectStrategy.sendRedirect(request, response, redirectUri as String?)
    }

    fun isAuthorizedUri(uri: String): Boolean =
        URI.create(uri).run { host == authorizedUri.host && port == authorizedUri.port }
}
