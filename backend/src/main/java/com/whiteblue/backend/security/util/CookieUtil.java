package com.whiteblue.backend.security.util;

import org.springframework.util.SerializationUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

public class CookieUtil {
    public static Optional<Cookie> getCookie(HttpServletRequest request, String key) {
        Optional<Cookie[]> optionalCookies = Optional.ofNullable(request.getCookies());
        return optionalCookies.flatMap(cookies -> Arrays.stream(cookies)
                                                        .filter(cookie -> cookie.getName()
                                                                                .equals(key))
                                                        .findAny());
    }

    public static void addCookie(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        //        cookie.setSecure(true);
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String key) {
        Arrays.stream(request.getCookies())
              .filter(cookie -> cookie.getName()
                                      .equals(key))
              .forEach(cookie -> {
                  cookie.setValue(null);
                  cookie.setMaxAge(0);
                  response.addCookie(cookie);
              });
    }

    public static String serialize(Object object) {
        return Base64.getUrlEncoder()
                     .encodeToString(SerializationUtils.serialize(object));
    }

    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(SerializationUtils.deserialize(
                Base64.getUrlDecoder()
                      .decode(cookie.getValue())));
    }
}
