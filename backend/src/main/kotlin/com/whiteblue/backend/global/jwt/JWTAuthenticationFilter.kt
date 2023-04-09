package com.whiteblue.backend.global.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JWTAuthenticationFilter(private val jwtProvider: JWTProvider) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        request.getHeader("Authorization")
            ?.let {
                if (it.startsWith("Bearer")) {
                    it.substring(7)
                } else null
            }
            ?.also {
                if (jwtProvider.validateToken(it)) {
                    SecurityContextHolder.getContext().authentication = jwtProvider.getAuthentication(it)
                }
            }

        filterChain.doFilter(request, response)
    }
}
