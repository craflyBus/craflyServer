package com.crafly.craflyserver.user.adapter.out.repository

import com.crafly.craflyserver.user.adapter.out.entity.AuthEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface AuthRepository : JpaRepository<AuthEntity, String> {
    @Query("SELECT A " +
            "FROM AuthEntity A " +
            "WHERE A.id = :id")
    fun findByIdentity(id: String): AuthEntity?
}