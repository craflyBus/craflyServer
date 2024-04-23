package com.crafly.craflyserver.user.adapter.out.repository

import com.crafly.craflyserver.user.adapter.out.entity.KakaoAuthEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface KakaoAuthRepository : JpaRepository<KakaoAuthEntity, String> {
}