package com.crafly.craflyserver.user.application.port.`in`;

import com.crafly.craflyserver.user.application.port.`in`.parameter.register.RegisterFullUserCommand
import com.crafly.craflyserver.user.application.port.`in`.parameter.register.RegisterUserCommand
import com.crafly.craflyserver.user.application.port.`in`.parameter.update.UpdateUserCommand

interface ManipulateUserUseCase {
    fun registerUser(user: RegisterFullUserCommand)
    fun updateUser(code: String, user: UpdateUserCommand)
}
