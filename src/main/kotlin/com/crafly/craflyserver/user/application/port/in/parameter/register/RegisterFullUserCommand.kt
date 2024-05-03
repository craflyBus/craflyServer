package com.crafly.craflyserver.user.application.port.`in`.parameter.register

data class RegisterFullUserCommand (
    val user: RegisterUserCommand,
    val auth: RegisterAuthCommand,
)