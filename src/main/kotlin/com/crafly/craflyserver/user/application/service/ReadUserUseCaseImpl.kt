package com.crafly.craflyserver.user.application.service;

import com.crafly.craflyserver.user.application.port.`in`.ReadUserQuery
import com.crafly.craflyserver.user.application.port.out.ReadUserPort
import com.crafly.craflyserver.user.domain.user.User
import com.crafly.craflyserver.util.annotation.UseCase
import org.springframework.transaction.annotation.Transactional

@Transactional
@UseCase
internal class ReadUserUseCaseImpl (
        private val readUserPort: ReadUserPort,
): ReadUserQuery {

    override fun getUser(code: String): User {
        return readUserPort.loadUserByCode(code)
    }
}
