package com.crafly.craflyserver.user.application.port.`in`;

import com.crafly.craflyserver.user.application.port.`in`.parameter.user.register.RegisterFullUserCommand
import com.crafly.craflyserver.user.application.port.`in`.parameter.user.register.RegisterUserCommand
import com.crafly.craflyserver.user.application.port.`in`.parameter.user.update.UpdateUserCommand

interface ManipulateUserUseCase {
    fun registerUser(user: RegisterFullUserCommand)
    fun updateUser(code: String, user: UpdateUserCommand)
}
