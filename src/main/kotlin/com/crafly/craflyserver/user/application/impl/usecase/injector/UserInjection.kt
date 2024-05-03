package com.crafly.craflyserver.user.application.impl.usecase.injector

import com.crafly.craflyserver.global.annotation.Injector
import com.crafly.craflyserver.global.model.exception.BackendException
import com.crafly.craflyserver.user.application.port.`in`.parameter.register.RegisterAuthCommand
import com.crafly.craflyserver.user.application.port.`in`.parameter.register.RegisterKakaoAuthCommand
import com.crafly.craflyserver.user.application.port.`in`.parameter.register.RegisterUserCommand
import com.crafly.craflyserver.user.domain.User
import com.crafly.craflyserver.user.domain.UserActivate
import com.crafly.craflyserver.user.domain.auth.Auth
import com.crafly.craflyserver.user.domain.auth.KakaoAuth
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

@Injector
class UserInjection {
    fun injectionUser(code: String, user: RegisterUserCommand): User {
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
    fun injectionAuth(code: String, auth: RegisterAuthCommand, password: String?): Auth {
        if (auth == null) throw BackendException(HttpStatus.BAD_REQUEST, "Auth is Null")
        return Auth(
                code = code,
                id = auth.id,
                password = password ?: auth.password
        )
    }
    fun injectionKakaoAuth(code: String, auth: RegisterKakaoAuthCommand?): KakaoAuth {
        if (auth == null) throw BackendException(HttpStatus.BAD_REQUEST, "Auth is Null")
        return KakaoAuth(
                code = code,
                id = auth.id,
        )
    }
}