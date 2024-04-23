package com.crafly.craflyserver.user.application.port.`in`

import com.crafly.craflyserver.user.domain.user.User
import com.crafly.craflyserver.user.domain.user.auth.Auth
import com.crafly.craflyserver.user.domain.user.auth.KakaoAuth

data class RegisterUserCommand (
        val user: User,
        val auth: Auth?,
        val kakaoAuth: KakaoAuth?,
) {
    fun integrity(): Boolean {
        return true;
    }
}