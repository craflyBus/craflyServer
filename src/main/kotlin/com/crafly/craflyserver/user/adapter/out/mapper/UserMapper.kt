package com.crafly.craflyserver.user.adapter.out.mapper

import com.crafly.craflyserver.user.adapter.out.entity.UserEntity
import com.crafly.craflyserver.user.domain.user.User
import com.crafly.craflyserver.global.annotation.Mapper

@Mapper
class UserMapper {
    fun toDomain(user: UserEntity): User {
        return User(
            user.code,
            user.nickname,
            user.telephone,
            user.postCode,
            user.address,
            user.addressDetail,
            user.type,
            user.activate,
            user.createTime,
            user.withdrawTime
        )
    }

    fun toEntity(user: User): UserEntity {
        return UserEntity(
            user.code,
            user.nickname,
            user.telephone,
            user.postCode,
            user.address,
            user.addressDetail,
            user.type,
            user.activate,
            user.createTime,
            user.withdrawTime
        )
    }
}