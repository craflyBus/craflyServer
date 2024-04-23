package com.crafly.craflyserver.user.adapter.out.repository

import com.crafly.craflyserver.user.adapter.out.entity.AuthEntity
import org.springframework.data.jpa.repository.JpaRepository
interface AuthRepository : JpaRepository<AuthEntity, String> {
}