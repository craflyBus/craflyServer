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
    var nickname: String,
    var telephone: String,
    var postCode: String?,
    var address: String?,
    var addressDetail: String?,
    var type: UserType = UserType.U,
    var activate: UserActivate = UserActivate.N,
    @CreatedDate
    val createTime: LocalDateTime = LocalDateTime.now(),
    var withdrawTime: LocalDateTime? = null
) {
    fun update(
        nickname: String,
        telephone: String,
        postCode: String?,
        address: String?,
        addressDetail: String?
    ) {
        this.nickname = nickname
        this.telephone = telephone
        this.postCode = postCode
        this.address = address
        this.addressDetail = addressDetail
    }

    fun withdraw() {
        this.withdrawTime = LocalDateTime.now()
    }
}
