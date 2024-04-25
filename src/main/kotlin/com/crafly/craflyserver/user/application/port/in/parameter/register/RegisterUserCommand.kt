package com.crafly.craflyserver.user.application.port.`in`.parameter.register

data class RegisterUserCommand (
        val user: RegisterUser,
        val auth: RegisterAuth?,
        val kakaoAuth: RegisterKakaoAuth?,
)