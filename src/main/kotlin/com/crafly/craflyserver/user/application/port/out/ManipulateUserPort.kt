package com.crafly.craflyserver.user.application.port.out;

import com.crafly.craflyserver.user.application.port.`in`.parameter.update.UpdateUserCommand
import com.crafly.craflyserver.user.domain.auth.include.UserAuth

interface ManipulateUserPort {
    fun registerUser(user: UserAuth)
    fun updateUser(code: String, user: UpdateUserCommand)
}
