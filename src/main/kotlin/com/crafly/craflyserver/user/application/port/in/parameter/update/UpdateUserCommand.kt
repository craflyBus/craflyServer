package com.crafly.craflyserver.user.application.port.`in`.parameter.update

data class UpdateUserCommand (
        val nickname: String,
        val telephone: String,
        val postCode: String?,
        val address: String?,
        val addressDetail: String?,
)