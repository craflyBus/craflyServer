package com.crafly.craflyserver.user.domain.auth.include;

import com.crafly.craflyserver.user.domain.User
import com.crafly.craflyserver.user.domain.auth.Auth

data class UserAuth (
        val user: User,
        val auth: Auth,
)