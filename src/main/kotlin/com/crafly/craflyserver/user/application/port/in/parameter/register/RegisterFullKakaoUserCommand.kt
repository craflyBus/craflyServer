package com.crafly.craflyserver.user.application.port.`in`.parameter.register

data class RegisterFullKakaoUserCommand (
    val user: RegisterUserCommand,
    val auth: RegisterKakaoAuthCommand,
)