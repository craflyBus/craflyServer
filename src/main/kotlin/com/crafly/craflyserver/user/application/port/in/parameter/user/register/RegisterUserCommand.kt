package com.crafly.craflyserver.user.application.port.`in`.parameter.user.register

import com.crafly.craflyserver.user.domain.UserType

data class RegisterUserCommand (
        val nickname: String,
        val telephone: String,
        val postCode: String?,
        val address: String?,
        val addressDetail: String?,
        val type: UserType,
)