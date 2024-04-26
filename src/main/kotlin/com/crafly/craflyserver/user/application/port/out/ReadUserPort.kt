package com.crafly.craflyserver.user.application.port.out;

import com.crafly.craflyserver.user.domain.User

interface ReadUserPort {
    fun readUserByCode(code: String): User
}
