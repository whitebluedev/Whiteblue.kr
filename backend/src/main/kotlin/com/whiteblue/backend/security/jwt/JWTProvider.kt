package com.whiteblue.backend.security.jwt

import com.whiteblue.backend.config.ApplicationProperties
import com.whiteblue.backend.domain.user.UserRepository
import com.whiteblue.backend.security.oAuth.OAuthUser
import io.jsonwebtoken.*
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class JWTProvider(
    private val applicationProperties: ApplicationProperties,

    private val userRepository: UserRepository
) {
    companion object {
        const val HOUR = 1000L * 60 * 60
    }

    private val secretKey = applicationProperties.secretKey

    private val accessTokenExpire = applicationProperties.accessTokenExpire * HOUR

    private val refreshTokenExpire = applicationProperties.refreshTokenExpire * HOUR

    fun createAccessToken(authentication: Authentication): String {
        val now = Date()
        val user = authentication.principal as OAuthUser

        return Jwts.builder()
            .setSubject("access_token")
            .setIssuer("Whiteblue")
            .setIssuedAt(now)
            .claim("id", user.id)
            .claim("username", user.username)
            .claim("name", user.name)
            .claim("role", authentication.authorities.joinToString(",") { it.authority })
            .setExpiration(Date(now.time + accessTokenExpire))
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact()
    }

    @Transactional
    fun createRefreshToken(authentication: Authentication): String {
        val now = Date()
        val refreshToken = Jwts.builder()
            .setSubject("refresh_token")
            .setIssuer("Whiteblue")
            .setIssuedAt(now)
            .setExpiration(Date(now.time + refreshTokenExpire))
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact()
        val user = authentication.principal as OAuthUser

        userRepository.updateRefreshToken(user.id, refreshToken)

        return refreshToken
    }

    fun getAuthentication(accessToken: String): Authentication {
        val claims = try {
            Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(accessToken)
                .body
        } catch (exception: ExpiredJwtException) {
            exception.claims
        }
        val authorities = claims["role"]
            .toString()
            .split(",")
            .map(::SimpleGrantedAuthority)
        val principal = OAuthUser(
            id = (claims["id"] as Int).toLong(),
            username = claims["username"] as String,
            name = claims["name"] as String,
            authorities = authorities,
            attributes = null
        )

        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
            return true
        } catch (exception: ExpiredJwtException) {
            println("만료된 토크입니다")
        } catch (exception: UnsupportedJwtException) {
            println("지원되지 않는 토크입니다")
        } catch (exception: IllegalStateException) {
            println("토큰이 잘못되었습니다")
        } catch (exception: SignatureException) {
            println("토큰 서명이 적절하지 않습니다.")
        }
        return false
    }
}
