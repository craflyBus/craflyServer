package com.crafly.craflyserver.user.application.port.`in`;

interface RegisterUserUseCase {
    fun registerUser(registerUserCommand: RegisterUserCommand)
}
