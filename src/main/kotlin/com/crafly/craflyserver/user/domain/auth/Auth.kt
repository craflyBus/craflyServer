package com.crafly.craflyserver.user.domain.auth

data class Auth(
        val code: String,
        val id: String,
        val password: String,
)
