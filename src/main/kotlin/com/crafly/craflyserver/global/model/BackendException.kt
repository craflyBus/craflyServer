package com.crafly.craflyserver.global.model

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

sealed class BackendException(
        val code: HttpStatus,
        override val message: String,
): RuntimeException(message)

data class MissingException(
        override val message: String = "no result",
): BackendException(HttpStatus.NOT_FOUND, message)

data class NotIncludeException(
        override val message: String = "data not include",
): BackendException(HttpStatus.BAD_REQUEST, message)

data class UnauthorizedException(
        override val message: String = "access token invalid",
): BackendException(HttpStatus.UNAUTHORIZED, message)