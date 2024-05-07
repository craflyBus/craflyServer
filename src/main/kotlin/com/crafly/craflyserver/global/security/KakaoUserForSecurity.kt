package com.crafly.craflyserver.global.security;

import com.crafly.craflyserver.user.domain.UserActivate
import com.crafly.craflyserver.user.domain.UserType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

data class KakaoUserForSecurity(
    private val code: String,
    private val kakaoCode: String,
    private val type: UserType = UserType.U,
    private val activate: UserActivate = UserActivate.N,
    private val withdrawTime: LocalDateTime? = null,
) {
    fun getAuthorities(): Collection<SimpleGrantedAuthority> {
        val authorities:Collection<SimpleGrantedAuthority> = ArrayList()
        authorities.plus(SimpleGrantedAuthority("ROLE_GUEST"))

        return authorities
    }

    fun getKakaoCode(): String {
        return kakaoCode
    }

    fun isAccountNonExpired(): Boolean {
        return withdrawTime == null
    }

    fun isEnabled(): Boolean {
        return activate == UserActivate.Y
    }
}
