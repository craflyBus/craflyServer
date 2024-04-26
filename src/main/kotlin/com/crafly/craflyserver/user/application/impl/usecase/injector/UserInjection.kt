package com.crafly.craflyserver.user.application.impl.usecase.injector

import com.crafly.craflyserver.global.annotation.Injector
import com.crafly.craflyserver.user.application.port.`in`.parameter.user.register.RegisterAuthCommand
import com.crafly.craflyserver.user.application.port.`in`.parameter.user.register.RegisterKakaoAuthCommand
import com.crafly.craflyserver.user.application.port.`in`.parameter.user.register.RegisterUserCommand
import com.crafly.craflyserver.user.application.port.`in`.parameter.user.update.UpdateUserCommand
import com.crafly.craflyserver.user.domain.user.User
import com.crafly.craflyserver.user.domain.user.UserActivate
import com.crafly.craflyserver.user.domain.user.auth.Auth
import com.crafly.craflyserver.user.domain.user.auth.KakaoAuth
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
    fun injectionAuth(code: String, auth: RegisterAuthCommand?): Auth? {
        if (auth == null) return null
        return Auth(
                code = code,
                id = auth.id,
                password = auth.password
        )
    }
    fun injectionKakaoAuth(code: String, auth: RegisterKakaoAuthCommand?): KakaoAuth? {
        if (auth == null) return null
        return KakaoAuth(
                code = code,
                id = auth.id,
        )
    }
}