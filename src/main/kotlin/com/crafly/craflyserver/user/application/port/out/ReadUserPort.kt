package com.crafly.craflyserver.user.application.port.out;

import com.crafly.craflyserver.user.domain.user.User

interface ReadUserPort {
    fun readUserByCode(code: String): User
}
