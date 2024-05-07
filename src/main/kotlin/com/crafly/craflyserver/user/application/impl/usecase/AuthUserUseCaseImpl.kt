package com.crafly.craflyserver.user.application.impl.usecase

import com.crafly.craflyserver.global.annotation.UseCase
import com.crafly.craflyserver.global.model.exception.BackendException
import com.crafly.craflyserver.global.model.exception.UnknownUserException
import com.crafly.craflyserver.global.security.service.UserForSecurityService
import com.crafly.craflyserver.global.util.GeneratorCode
import com.crafly.craflyserver.user.application.impl.usecase.injector.UserInjection
import com.crafly.craflyserver.user.application.port.`in`.AuthUserUseCase
import com.crafly.craflyserver.user.application.port.`in`.parameter.register.RegisterFullKakaoUserCommand
import com.crafly.craflyserver.user.application.port.out.ManipulateUserPort
import com.crafly.craflyserver.user.domain.auth.include.KakaoUserAuth
import com.crafly.craflyserver.user.domain.auth.include.UserAuth
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.transaction.annotation.Transactional

@Transactional
@UseCase
class AuthUserUseCaseImpl(
    private val userForSecurityService: UserForSecurityService,
): AuthUserUseCase {
    override fun authKakaoUser(code: String) {
        try {
            userForSecurityService.loadUserByKakaoCode(code)
        } catch (e: UnknownUserException) {
            throw BackendException(HttpStatus.BAD_REQUEST, e.message)
        }
    }
}