package com.crafly.craflyserver.user.domain.user;

import com.crafly.craflyserver.user.domain.user.auth.Auth
import com.crafly.craflyserver.user.domain.user.auth.KakaoAuth

data class UserAuth (
        val user: User,
        val auth: Auth?,
        val kakaoAuth: KakaoAuth?,
)