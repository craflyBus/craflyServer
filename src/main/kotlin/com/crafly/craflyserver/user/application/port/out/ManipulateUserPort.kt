package com.crafly.craflyserver.user.application.port.out;

import com.crafly.craflyserver.user.domain.user.UserAuth

interface ManipulateUserPort {
    fun registerUser(user: UserAuth)
}
