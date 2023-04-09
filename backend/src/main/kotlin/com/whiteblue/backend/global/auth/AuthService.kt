package com.whiteblue.backend.global.auth

import com.whiteblue.backend.domain.user.repository.UserRepository
import com.whiteblue.backend.global.auth.dto.RefreshTokenResponse
import com.whiteblue.backend.global.jwt.JWTProvider
import com.whiteblue.backend.global.oAuth.OAuthUser
import com.whiteblue.backend.global.util.CookieUtil
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class AuthService(
    private val userRepository: UserRepository,

    private val jwtProvider: JWTProvider
) {
    fun refreshToken(request: HttpServletRequest, accessToken: String): RefreshTokenResponse? {
        val refreshToken = CookieUtil.getCookie(request, "refresh_token")?.value
            ?.also {
                if (!jwtProvider.validateToken(it)) {
                    throw RuntimeException("유효하지 않은 Refresh Token입니다.")
                }
            }
            ?: throw RuntimeException("Refresh Token이 존재하지 않습니다.")
        val authentication = jwtProvider.getAuthentication(accessToken)

        return userRepository.getRefreshTokenById((authentication.principal as OAuthUser).id)
            ?.let {
                if (it == refreshToken) {
                    RefreshTokenResponse(accessToken = jwtProvider.createAccessToken(authentication))
                } else throw RuntimeException("Refresh Token이 일치하지 않습니다.")
            } ?: throw RuntimeException("Refresh Token이 존재하지 않습니다.")
    }
}
