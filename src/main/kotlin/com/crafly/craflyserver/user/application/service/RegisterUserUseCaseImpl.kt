package com.crafly.craflyserver.user.application.service;

import com.crafly.craflyserver.user.application.port.`in`.RegisterUserCommand
import com.crafly.craflyserver.user.application.port.`in`.RegisterUserUseCase
import com.crafly.craflyserver.user.application.port.out.ManipulateUserPort
import com.crafly.craflyserver.user.domain.user.UserAuth
import com.crafly.craflyserver.util.annotation.UseCase
import org.springframework.transaction.annotation.Transactional

@Transactional
@UseCase
class RegisterUserUseCaseImpl (
    private val manipulateUserPort: ManipulateUserPort,
): RegisterUserUseCase {
    override fun registerUser(registerUserCommand: RegisterUserCommand) {
        val user = UserAuth(
                user = registerUserCommand.user,
                auth = registerUserCommand.auth,
                kakaoAuth = registerUserCommand.kakaoAuth
        )
        manipulateUserPort.registerUser(user)
    }
}
