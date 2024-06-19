package com.crafly.craflyserver.user.application.port.`in`;

import com.crafly.craflyserver.user.domain.User

interface ReadUserQuery {
    fun getUser(code: String): User
    fun getCode(id: String): String
}
