package com.crafly.craflyserver.user.adapter.out.repository;

import com.crafly.craflyserver.user.adapter.out.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

interface UserRepository : JpaRepository<UserEntity, String> {
    fun findByCode(code: String) : UserEntity?
    @Query("SELECT U.nickname, U.telephone, U.postCode, " +
            "U.address, U.addressDetail, U.type " +
            "FROM UserEntity U " +
            "INNER JOIN AuthEntity A ON A.code = U.code " +
            "WHERE A.id = :id")
    fun findByAuthId(@Param("id") id: String) : UserEntity

    @Query("SELECT U.nickname, U.telephone, U.postCode, " +
            "U.address, U.addressDetail, U.type " +
            "FROM UserEntity U " +
            "INNER JOIN KakaoAuthEntity A ON A.code = U.code " +
            "WHERE A.id = :id")
    fun findByKakaoAuthId(id: String) : UserEntity
}
