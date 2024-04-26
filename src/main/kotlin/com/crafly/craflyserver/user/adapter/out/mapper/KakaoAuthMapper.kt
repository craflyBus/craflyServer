package com.crafly.craflyserver.user.adapter.out.mapper

import com.crafly.craflyserver.user.adapter.out.entity.KakaoAuthEntity
import com.crafly.craflyserver.user.domain.user.auth.KakaoAuth
import com.crafly.craflyserver.global.annotation.Mapper

@Mapper
class KakaoAuthMapper {
    fun toDomain(auth: KakaoAuthEntity): KakaoAuth {
        return KakaoAuth(
            auth.code,
            auth.id
        )
    }

    fun toEntity(auth: KakaoAuth): KakaoAuthEntity {
        return KakaoAuthEntity(
            auth.code,
            auth.id
        )
    }
}