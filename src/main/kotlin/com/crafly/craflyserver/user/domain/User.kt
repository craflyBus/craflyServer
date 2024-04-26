package com.crafly.craflyserver.user.domain;

import java.time.LocalDateTime

data class User (
        val code: String,
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
