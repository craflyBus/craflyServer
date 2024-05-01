package com.crafly.craflyserver.user.adapter.out.repository;

import com.crafly.craflyserver.user.adapter.out.entity.UserEntity
import com.crafly.craflyserver.user.adapter.out.entity.UserForSecurityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserForSecurityRepository : JpaRepository<UserForSecurityEntity, String> {
    @Query("SELECT U.code, A.id, A.password, " +
            "U.activate, U.type, U.withdrawTime " +
            "FROM UserEntity U " +
            "INNER JOIN AuthEntity A ON A.code = U.code " +
            "WHERE A.id = :id")
    fun findAuthById(@Param("id") id: String) : UserForSecurityEntity
}
