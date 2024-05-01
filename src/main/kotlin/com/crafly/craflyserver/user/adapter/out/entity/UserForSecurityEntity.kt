package com.crafly.craflyserver.user.adapter.out.entity;

import com.crafly.craflyserver.user.domain.UserActivate
import com.crafly.craflyserver.user.domain.UserType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime

@Entity
data class UserForSecurityEntity(
    @Id
    val code: String,
    val id: String,
    val pw: String,
    val type: UserType = UserType.U,
    val activate: UserActivate = UserActivate.N,
    val withdrawTime: LocalDateTime? = null,
):UserDetails {
    override fun getAuthorities(): Collection<SimpleGrantedAuthority> {
        val authorities:Collection<SimpleGrantedAuthority> = ArrayList()
        authorities.plus(SimpleGrantedAuthority("ROLE_GUEST"))

        return authorities
    }

    override fun getPassword(): String {
        return pw
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
