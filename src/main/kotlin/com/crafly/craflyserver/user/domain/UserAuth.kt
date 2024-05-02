package com.crafly.craflyserver.user.domain;

import com.crafly.craflyserver.user.domain.auth.Auth
import com.crafly.craflyserver.user.domain.auth.KakaoAuth

data class UserAuth (
        val user: User,
        val auth: Auth,
)