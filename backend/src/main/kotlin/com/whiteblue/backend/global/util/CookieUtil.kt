package com.whiteblue.backend.global.util

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

object CookieUtil {
    fun getCookie(request: HttpServletRequest, key: String): Cookie? = request.cookies?.find { it.name.equals(key) }

    fun addCookie(response: HttpServletResponse, key: String, value: String) =
        response.addCookie(
            Cookie(key, value)
                .apply {
                    path = "/"
                    isHttpOnly = true
                    secure = true
                })

    fun deleteCookie(request: HttpServletRequest, response: HttpServletResponse, key: String) =
        getCookie(request, key)
            ?.also {
                it.value = null
                it.maxAge = 0
                response.addCookie(it)
            }
}
