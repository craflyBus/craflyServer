package com.crafly.craflyserver.global.security.provider;

import jakarta.servlet.http.Cookie
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Component

@Component
class CookieProvider {
    @Value("\${jwt.refresh-expired-time}")
    private val refreshTokenExpiredTime: String? = null

    @Value("\${jwt.access-expired-time}")
    private val accessTokenExpiredTime: String? = null

    fun createRefreshTokenCookie(refreshToken: String?): ResponseCookie {
        return ResponseCookie.from("refresh-token", refreshToken!!)
            .httpOnly(true)
            .secure(false)
            .path("/")
            .maxAge(refreshTokenExpiredTime!!.toLong()).build()
    }

    fun createAccessTokenCookie(accessToken: String?): ResponseCookie {
        return ResponseCookie.from("access-token", accessToken!!)
            .httpOnly(true)
            .secure(false)
            .path("/")
            .maxAge(accessTokenExpiredTime!!.toLong()).build()
    }

    fun removeRefreshTokenCookie(): ResponseCookie {
        return ResponseCookie.from("refresh-token")
            .value(null)
            .maxAge(0)
            .path("/")
            .build()
    }

    fun removeAccessTokenCookie(): ResponseCookie {
        return ResponseCookie.from("access-token")
            .value(null)
            .maxAge(0)
            .path("/")
            .build()
    }

    fun of(responseCookie: ResponseCookie): Cookie {
        val cookie = Cookie(responseCookie.name, responseCookie.value)
        cookie.path = responseCookie.path
        cookie.secure = responseCookie.isSecure
        cookie.isHttpOnly = responseCookie.isHttpOnly
        cookie.maxAge = responseCookie.maxAge.seconds.toInt()
        return cookie
    }
}
