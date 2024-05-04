package com.crafly.craflyserver.global.security.provider

import com.crafly.craflyserver.global.security.token.TokenType
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import jakarta.xml.bind.DatatypeConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenProvider(
    private val userForSecurityService: UserDetailsService,
    @Value("\${jwt.access-secret-key")
    private val accessSecret: String,
    @Value("\${jwt.refresh-secret-key")
    private val refreshSecret: String,
    @Value("\${jwt.access-expired-time}")
    private val accessExpiredTime: Long,
    @Value("\${jwt.refresh-expired-time}")
    private val refreshExpiredTime: Long,
) {
    fun generateAccessToken(userCode: String, uri: String, roles: List<String>): String {
        val claims: Claims = Jwts.claims().setSubject(userCode)
        claims["usercode"] = userCode
        claims["roles"] = roles

        val now = Date()

        val build: JwtBuilder = Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setClaims(claims)
            .setIssuedAt(now)
            .setIssuer(uri)
            .setExpiration(Date(now.time + accessExpiredTime))
            .signWith(getSigningKey(TokenType.ACCESS_TOKEN), SignatureAlgorithm.HS256)

        return build.compact()
    }

    fun generateRefreshToken(uuid: String?): String {
        val claims = Jwts.claims().setSubject(uuid)

        val build: JwtBuilder = Jwts.builder()
            .addClaims(claims)
            .setExpiration(
                Date(System.currentTimeMillis() + refreshExpiredTime)
            )
            .setIssuedAt(Date())
            .signWith(getSigningKey(TokenType.REFRESH_TOKEN), SignatureAlgorithm.HS256)

        return build.compact()
    }

    private fun getSigningKey(type: TokenType): Key {
        return Keys.hmacShaKeyFor(
            when(type) {
                TokenType.ACCESS_TOKEN -> accessSecret
                TokenType.REFRESH_TOKEN -> refreshSecret
            }.toByteArray(Charsets.UTF_16)
        )
    }
}