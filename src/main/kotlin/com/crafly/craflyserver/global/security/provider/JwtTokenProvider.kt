package com.crafly.craflyserver.global.security.provider

import com.crafly.craflyserver.global.security.service.UserForSecurityService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
    private val userForSecurityService: UserForSecurityService,
    @Value("\${jwt.secret-key")
    private val secretKey: String,
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

        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setClaims(claims)
            .setIssuedAt(now)
            .setIssuer(uri)
            .setExpiration(Date(now.time + accessExpiredTime))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun generateRefreshToken(uuid: String?): String {
        val claims = Jwts.claims().setSubject(uuid)
        return Jwts.builder()
            .addClaims(claims)
            .setExpiration(
                Date(System.currentTimeMillis() + refreshExpiredTime) // (100일) 1시간
            )
            .setIssuedAt(Date())
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails = userForSecurityService.loadUserByUsername(getUserCode(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUserCode(token: String): String {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
    }

    fun resolveToken(request: HttpServletRequest): String? {
        return request.getHeader("Authorization")
    }

    fun validateToken(jwtToken: String): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }
}