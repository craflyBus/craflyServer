package com.crafly.craflyserver.user.adapter.out.mapper

import com.crafly.craflyserver.user.adapter.out.entity.AuthEntity
import com.crafly.craflyserver.user.adapter.out.entity.UserEntity
import com.crafly.craflyserver.user.domain.user.User
import com.crafly.craflyserver.user.domain.user.auth.Auth
import com.crafly.craflyserver.util.annotation.Mapper

@Mapper
class AuthMapper {
    fun toDomain(auth: AuthEntity): Auth {
        return Auth(
            auth.code,
            auth.id,
            auth.password
        )
    }

    fun toEntity(auth: Auth): AuthEntity {
        return AuthEntity(
            auth.code,
            auth.id,
            auth.password
        )
    }
}