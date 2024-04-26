package com.crafly.craflyserver.global.model.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

sealed class BackendException(
        val status: HttpStatus,
        override val message: String,
): RuntimeException(message)

object InternalServerException: BackendException(
        HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error"
)

data class MissingException(
        override val message: String = "No Result",
): BackendException(HttpStatus.NOT_FOUND, message)

data class NotIncludeException(
        override val message: String = "Data Not Include",
): BackendException(HttpStatus.BAD_REQUEST, message)

data class UnauthorizedException(
        override val message: String = "Access Token Invalid",
): BackendException(HttpStatus.UNAUTHORIZED, message)