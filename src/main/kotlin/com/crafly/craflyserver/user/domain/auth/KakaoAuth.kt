package com.crafly.craflyserver.user.domain.auth;

data class KakaoAuth (
        val code: String,
        val kakaoCode: String,
)