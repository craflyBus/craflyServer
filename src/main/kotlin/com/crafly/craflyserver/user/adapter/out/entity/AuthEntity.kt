package com.crafly.craflyserver.user.adapter.out.entity

import com.crafly.craflyserver.user.domain.auth.Auth
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name="auth")
@Entity
data class AuthEntity(
    @Id
    val code: String,
    val id: String,
    val password: String
) {
    fun mapToAuth() = Auth(
        code = code,
        id = id,
        password = password
    )
}