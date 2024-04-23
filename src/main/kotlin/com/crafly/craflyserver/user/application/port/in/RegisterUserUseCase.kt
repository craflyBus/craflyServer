package com.crafly.craflyserver.user.application.port.`in`;

import com.crafly.craflyserver.user.application.port.`in`.parameter.register.RegisterUserCommand

interface RegisterUserUseCase {
    fun registerUser(registerUserCommand: RegisterUserCommand)
}
