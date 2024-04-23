package com.crafly.craflyserver.user.adapter.out.entity

import com.crafly.craflyserver.user.domain.user.auth.KakaoAuth
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name="kakao_auth")
@Entity
internal class KakaoAuthEntity {
    @Id
    var code: String = ""
    var id: String = ""

    constructor()
    constructor(kakaoAuth: KakaoAuth) {
        code = kakaoAuth.code
        id = kakaoAuth.id
    }

    fun mapToKakaoAuth() = KakaoAuth(
            code = code,
            id = id,
    )
}