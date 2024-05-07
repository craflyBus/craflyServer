package com.crafly.craflyserver.user.domain.auth.include;

import com.crafly.craflyserver.user.domain.User
import com.crafly.craflyserver.user.domain.auth.KakaoAuth

data class KakaoUserAuth (
        val user: User,
        val auth: KakaoAuth,
)