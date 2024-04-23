package com.crafly.craflyserver.user.adapter.out.entity

import com.crafly.craflyserver.user.domain.user.auth.Auth
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name="auth")
@Entity
internal class AuthEntity {
    @Id
    var code: String = ""
    var id: String = ""
    var password: String = ""

    constructor()
    constructor(auth: Auth) {
        code = auth.code
        id = auth.id
        password = auth.password
    }

    fun mapToAuth() = Auth(
        code = code,
        id = id,
        password = password
    )
}