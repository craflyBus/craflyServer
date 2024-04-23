package com.crafly.craflyserver.user.application.impl.usecase;

import com.crafly.craflyserver.user.application.port.`in`.parameter.register.RegisterUserCommand
import com.crafly.craflyserver.user.application.port.`in`.RegisterUserUseCase
import com.crafly.craflyserver.user.application.port.`in`.parameter.register.RegisterAuth
import com.crafly.craflyserver.user.application.port.`in`.parameter.register.RegisterKakaoAuth
import com.crafly.craflyserver.user.application.port.`in`.parameter.register.RegisterUser
import com.crafly.craflyserver.user.application.port.out.ManipulateUserPort
import com.crafly.craflyserver.user.domain.user.User
import com.crafly.craflyserver.user.domain.user.UserActivate
import com.crafly.craflyserver.user.domain.user.UserAuth
import com.crafly.craflyserver.user.domain.user.auth.Auth
import com.crafly.craflyserver.user.domain.user.auth.KakaoAuth
import com.crafly.craflyserver.util.annotation.UseCase
import com.crafly.craflyserver.util.generator.GeneratorCode
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional
@UseCase
class RegisterUserUseCaseImpl (
    private val manipulateUserPort: ManipulateUserPort,

    private val generatorCode: GeneratorCode
): RegisterUserUseCase {
    override fun registerUser(registerUserCommand: RegisterUserCommand) {
        val code = generatorCode.userCode()
        val user = UserAuth(
                user = injectionUser(code, registerUserCommand.user),
                auth = injectionAuth(code, registerUserCommand.auth),
                kakaoAuth = injectionKakaoAuth(code, registerUserCommand.kakaoAuth)
        )
        manipulateUserPort.registerUser(user)
    }

    fun injectionUser(code: String, user: RegisterUser): User {
        return User(
            code = code,
            nickname = user.nickname,
            telephone = user.telephone,
            postCode = user.postCode,
            address = user.address,
            addressDetail = user.addressDetail,
            type = user.type,
            activate = UserActivate.N,
            createTime = LocalDateTime.now(),
            withdrawTime = null,
        )
    }
    fun injectionAuth(code: String, auth: RegisterAuth?): Auth? {
        if (auth == null) return null
        return Auth(
            code = code,
            id = auth.id,
            password = auth.password
        )
    }
    fun injectionKakaoAuth(code: String, auth: RegisterKakaoAuth?): KakaoAuth? {
        if (auth == null) return null
        return KakaoAuth(
            code = code,
            id = auth.id,
        )
    }
}
