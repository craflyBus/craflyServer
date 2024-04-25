package com.crafly.craflyserver.user.adapter.out.entity;

import com.crafly.craflyserver.user.domain.user.User
import com.crafly.craflyserver.user.domain.user.UserActivate
import com.crafly.craflyserver.user.domain.user.UserType
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Table(name = "user")
@Entity
data class UserEntity(
    @Id
    val code: String,
    val nickname: String ,
    val telephone: String,
    val postCode: String?,
    val address: String?,
    val addressDetail: String?,
    val type: UserType = UserType.U,
    val activate: UserActivate = UserActivate.N,
    @CreatedDate
    val createTime: LocalDateTime = LocalDateTime.now(),
    val withdrawTime: LocalDateTime? = null      
) {
    

    fun mapToUser() = User(
        code = code,
        nickname = nickname,
        telephone = telephone,
        postCode = postCode,
        address = address,
        addressDetail = addressDetail,
        type = type,
        activate = activate,
        createTime = createTime,
        withdrawTime = withdrawTime
    )
}
