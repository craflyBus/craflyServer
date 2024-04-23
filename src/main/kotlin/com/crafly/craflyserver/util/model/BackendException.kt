package com.crafly.craflyserver.util.model

import java.lang.RuntimeException

sealed class BackendException(
        val code: Int,
        override val message: String,
): RuntimeException(message)

data class MissingException(
        override val message: String,
): BackendException(404, message)

data class NotIncludeException(
        override val message: String,
): BackendException(400, message)

data class UnauthorizedException(
        override val message: String = "access_token 이 유효하지 않습니다.",
): BackendException(401, message)