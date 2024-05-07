package com.crafly.craflyserver.user.application.impl.usecase;

import com.crafly.craflyserver.user.application.port.`in`.parameter.register.RegisterFullUserCommand
import com.crafly.craflyserver.user.application.port.`in`.ManipulateUserUseCase
import com.crafly.craflyserver.user.application.port.`in`.parameter.update.UpdateUserCommand
import com.crafly.craflyserver.user.application.port.out.ManipulateUserPort
import com.crafly.craflyserver.user.domain.auth.include.UserAuth
import com.crafly.craflyserver.global.annotation.UseCase
import com.crafly.craflyserver.global.util.GeneratorCode
import com.crafly.craflyserver.user.application.impl.usecase.injector.UserInjection
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.transaction.annotation.Transactional

@Transactional
@UseCase
class ManipulateUserUseCaseImpl (
    private val manipulateUserPort: ManipulateUserPort,

    private val userInjection: UserInjection,
    private val generatorCode: GeneratorCode,

    private val passwordEncoder: PasswordEncoder
): ManipulateUserUseCase {
    override fun registerUser(user: RegisterFullUserCommand) {
        val code = generatorCode.userCode()
        val fUser = UserAuth(
                user = userInjection.injectionUser(
                    code,
                    user.user
                ),
                auth = userInjection.injectionAuth(
                    code,
                    user.auth,
                    password = passwordEncoder.encode(user.auth.password)
                ),
        )
        manipulateUserPort.registerUser(fUser)
    }

    override fun updateUser(code: String, user: UpdateUserCommand) {
        manipulateUserPort.updateUser(code, user)
    }
}
