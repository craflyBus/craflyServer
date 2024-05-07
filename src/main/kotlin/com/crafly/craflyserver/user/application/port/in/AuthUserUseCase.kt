package com.crafly.craflyserver.user.application.port.`in`

interface AuthUserUseCase {
    fun authKakaoUser(code: String)
}