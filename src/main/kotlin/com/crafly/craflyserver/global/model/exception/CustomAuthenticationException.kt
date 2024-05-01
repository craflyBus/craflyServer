package com.crafly.craflyserver.global.model.exception

import org.springframework.security.core.AuthenticationException

class CustomAuthenticationException(private val msg: String): AuthenticationException(msg)