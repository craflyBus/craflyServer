package com.crafly.craflyserver.user.domain.user;

import java.time.LocalDateTime

data class User (
        val code: String,
        val authType: String,
        val nickname: String,
        val telephone: String,
        val postCode: String?,
        val address: String?,
        val addressDetail: String?,
        val type: UserType,
        val activate: UserActivate,
        val createTime: LocalDateTime,
        val withdrawTime: LocalDateTime?,
)
