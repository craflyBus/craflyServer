package com.crafly.craflyserver.user.application.impl.usecase;

import com.crafly.craflyserver.user.application.port.`in`.parameter.user.register.RegisterFullUserCommand
import com.crafly.craflyserver.user.application.port.`in`.ManipulateUserUseCase
import com.crafly.craflyserver.user.application.port.`in`.parameter.user.update.UpdateUserCommand
import com.crafly.craflyserver.user.application.port.out.ManipulateUserPort
import com.crafly.craflyserver.user.domain.user.UserAuth
import com.crafly.craflyserver.global.annotation.UseCase
import com.crafly.craflyserver.global.util.GeneratorCode
import com.crafly.craflyserver.user.application.impl.usecase.injector.UserInjection
import org.springframework.transaction.annotation.Transactional

@Transactional
@UseCase
class ManipulateUserUseCaseImpl (
    private val manipulateUserPort: ManipulateUserPort,

    private val userInjection: UserInjection,
    private val generatorCode: GeneratorCode
): ManipulateUserUseCase {
    override fun registerUser(user: RegisterFullUserCommand) {
        val code = generatorCode.userCode()
        val fUser = UserAuth(
                user = userInjection.injectionUser(code, user.user),
                auth = userInjection.injectionAuth(code, user.auth),
                kakaoAuth = userInjection.injectionKakaoAuth(code, user.kakaoAuth)
        )
        manipulateUserPort.registerUser(fUser)
    }

    override fun updateUser(code: String, user: UpdateUserCommand) {
        manipulateUserPort.updateUser(code, user)
    }
}
