package com.whiteblue.backend.security.oAuth

import com.whiteblue.backend.util.CookieUtil
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import org.springframework.stereotype.Component

@Component
class OAuthAuthorizationRequestRepository : AuthorizationRequestRepository<OAuth2AuthorizationRequest> {
    private var oAuth2AuthorizationRequest: OAuth2AuthorizationRequest? = null

    override fun loadAuthorizationRequest(request: HttpServletRequest?): OAuth2AuthorizationRequest? =
        oAuth2AuthorizationRequest

    override fun saveAuthorizationRequest(
        authorizationRequest: OAuth2AuthorizationRequest,
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        oAuth2AuthorizationRequest = authorizationRequest
        request.getParameter("redirect_uri")
            ?.let { CookieUtil.addCookie(response, "redirect_uri", it) }
    }

    override fun removeAuthorizationRequest(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): OAuth2AuthorizationRequest? = loadAuthorizationRequest(request)
}
