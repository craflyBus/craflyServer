package com.crafly.craflyserver.user.adapter.out.repository

import com.crafly.craflyserver.user.adapter.out.entity.KakaoAuthEntity
import org.springframework.data.jpa.repository.JpaRepository

interface KakaoAuthRepository : JpaRepository<KakaoAuthEntity, String> {
}