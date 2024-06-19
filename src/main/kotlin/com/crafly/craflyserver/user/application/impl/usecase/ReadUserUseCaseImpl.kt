package com.crafly.craflyserver.user.application.impl.usecase;

import com.crafly.craflyserver.user.application.port.`in`.ReadUserQuery
import com.crafly.craflyserver.user.application.port.out.ReadUserPort
import com.crafly.craflyserver.user.domain.User
import com.crafly.craflyserver.global.annotation.UseCase
import org.springframework.transaction.annotation.Transactional

@Transactional
@UseCase
internal class ReadUserUseCaseImpl (
        private val readUserPort: ReadUserPort,
): ReadUserQuery {

    override fun getUser(code: String): User {
        return readUserPort.readUserByCode(code)
    }

    override fun getCode(id: String): String {
        return readUserPort.readCodeById(id)
    }
}
