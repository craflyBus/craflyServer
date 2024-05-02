package com.crafly.craflyserver.global.security.filter

import com.crafly.craflyserver.global.model.exception.BackendException
import com.crafly.craflyserver.global.model.exception.CustomAuthenticationException
import com.crafly.craflyserver.global.model.exception.body.ExceptionResponse
import com.crafly.craflyserver.global.security.provider.CookieProvider
import com.crafly.craflyserver.global.security.provider.JwtTokenProvider
import com.crafly.craflyserver.user.adapter.out.entity.UserForSecurityEntity
import com.crafly.craflyserver.user.application.port.`in`.parameter.request.LoginRequest
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import java.util.stream.Collectors

class JwtAuthenticationFilter (
    private val jwtTokenProvider: JwtTokenProvider,
    private val cookieProvider: CookieProvider,
    private val authenticationManager: AuthenticationManager,
    private val objectMapper: ObjectMapper,

    private val loginUrl: String
): UsernamePasswordAuthenticationFilter() {
    init {
        super.setFilterProcessesUrl(loginUrl)
    }

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse?
    ): Authentication {
        val authentication: Authentication

        try {
            val credential = objectMapper.readValue(request.reader, LoginRequest::class.java)

            authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(credential.id, credential.password)
            )

            return authentication
        } catch (e: IOException) {
            throw RuntimeException(e.message)
        } catch (e: BackendException) {
            throw CustomAuthenticationException(e.message)
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val user: UserForSecurityEntity = authResult!!.principal as UserForSecurityEntity

        val roles: List<String> =
            user
                .authorities
                .stream()
                .map(
                    GrantedAuthority::getAuthority
                ).collect(
                    Collectors
                        .toList()
                )

        val userCode: String = user.username
        val refreshUUID = UUID.randomUUID().toString()

        val accessToken: String = jwtTokenProvider.generateAccessToken(userCode, request!!.requestURI, roles)
        val refreshToken: String = jwtTokenProvider.generateRefreshToken(refreshUUID)

        // todo redis에 추가하는 service 메소드 호출


        // 쿠키 설정
        val refreshTokenCookie = cookieProvider.createRefreshTokenCookie(refreshToken)
        val accessTokenCookie = cookieProvider.createAccessTokenCookie(accessToken)

        val refCookie = cookieProvider.of(refreshTokenCookie)
        val accCookie = cookieProvider.of(accessTokenCookie)

        response!!.contentType = MediaType.APPLICATION_JSON_VALUE
        response.addCookie(refCookie)
        response.addCookie(accCookie)

        ObjectMapper().writeValue(response.outputStream, Result.success("Success"))
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        failed: AuthenticationException
    ) {
        response.status = HttpStatus.FORBIDDEN.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        val exception: ExceptionResponse = ExceptionResponse(
            status = HttpStatus.FORBIDDEN.value(),
            message = failed.message ?: "Unknown Exception"
        )
        val objectMapper = ObjectMapper()
        response.writer.write(objectMapper.writeValueAsString(exception))
    }

    override fun getAuthenticationManager(): AuthenticationManager =
        authenticationManager
}