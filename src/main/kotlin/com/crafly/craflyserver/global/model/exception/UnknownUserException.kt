package com.crafly.craflyserver.global.model.exception

import java.lang.RuntimeException

data class UnknownUserException(
    override val message: String,
): RuntimeException(message)