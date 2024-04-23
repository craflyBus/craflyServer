package com.crafly.craflyserver.user.adapter.out.entity;

import com.crafly.craflyserver.user.domain.user.User
import com.crafly.craflyserver.user.domain.user.UserActivate
import com.crafly.craflyserver.user.domain.user.UserType
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Table(name = "user")
@Entity
internal class UserEntity {
    @Id
    var code: String = ""
    var authType: String = ""
    var nickname: String = ""
    var telephone: String = ""
    var postCode: String? = null
    var address: String? = null
    var addressDetail: String? = null
    var type: UserType = UserType.U
    var activate: UserActivate = UserActivate.N
    @CreatedDate
    var createTime: LocalDateTime = LocalDateTime.now()
    var withdrawTime: LocalDateTime? = null

    constructor()
    constructor(user: User) {
        code = user.code
        authType = user.authType
        nickname = user.nickname
        telephone = user.telephone
        postCode = user.postCode
        address = user.address
        addressDetail = user.addressDetail
        type = user.type
        activate = user.activate
        createTime = user.createTime
        withdrawTime = user.withdrawTime
    }

    fun mapToUser() = User(
            code = code,
            authType = authType,
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
