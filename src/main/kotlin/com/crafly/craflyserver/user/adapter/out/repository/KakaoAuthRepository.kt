package com.crafly.craflyserver.user.adapter.out.repository

import com.crafly.craflyserver.user.adapter.out.entity.KakaoAuthEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface KakaoAuthRepository : JpaRepository<KakaoAuthEntity, String> {
    fun findByKakaoCode(kakaoCode: String): KakaoAuthEntity?
}