package com.crafly.craflyserver.global.security.token

enum class TokenType(
    val value: String
) {
    ACCESS_TOKEN("access_token"),
    REFRESH_TOKEN("refresh_token")
}