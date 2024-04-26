package com.crafly.craflyserver.user.application.port.out;

import com.crafly.craflyserver.user.application.port.`in`.parameter.user.update.UpdateUserCommand
import com.crafly.craflyserver.user.domain.user.UserAuth

interface ManipulateUserPort {
    fun registerUser(user: UserAuth)
    fun updateUser(code: String, user: UpdateUserCommand)
}
