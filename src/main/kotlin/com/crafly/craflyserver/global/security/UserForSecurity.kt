package com.crafly.craflyserver.global.security;

import com.crafly.craflyserver.user.domain.UserActivate
import com.crafly.craflyserver.user.domain.UserType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

data class UserForSecurity(
    private val code: String,
    private val id: String,
    private val password: String,
    private val type: UserType = UserType.U,
    private val activate: UserActivate = UserActivate.N,
    private val withdrawTime: LocalDateTime? = null,
):UserDetails {
    override fun getAuthorities(): Collection<SimpleGrantedAuthority> {
        val authorities:Collection<SimpleGrantedAuthority> = ArrayList()
        authorities.plus(SimpleGrantedAuthority("ROLE_GUEST"))

        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return id
    }

    override fun isAccountNonExpired(): Boolean {
        return withdrawTime == null
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return activate == UserActivate.Y
    }
}
