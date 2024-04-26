package com.crafly.craflyserver.user.application.port.`in`.parameter.user.login

data class LoginCommand (
    val id: String,
    val password: String,
)