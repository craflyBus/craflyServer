package com.crafly.craflyserver.user.adapter.out.entity

import com.crafly.craflyserver.user.domain.auth.KakaoAuth
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name="kakao_auth")
@Entity
data class KakaoAuthEntity (
    @Id
    val code: String,
    val id: String
) {

    fun mapToKakaoAuth() = KakaoAuth(
        code = code,
        id = id,
    )
}