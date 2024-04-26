package com.crafly.craflyserver.global.model.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

data class BackendException(
        val status: HttpStatus,
        override val message: String,
): RuntimeException(message)

//data class UnauthorizedException(
//        override val message: String = "Access Token Invalid",
//): BackendException(HttpStatus.UNAUTHORIZED, message)