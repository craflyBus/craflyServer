package com.crafly.craflyserver.global.security.service

import com.crafly.craflyserver.user.adapter.out.entity.UserForSecurityEntity
import com.crafly.craflyserver.user.adapter.out.repository.UserForSecurityRepository
import com.crafly.craflyserver.user.adapter.out.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
data class UserForSecurityService(
    private val userForSecurityRepository: UserForSecurityRepository
): UserDetailsService {
    override fun loadUserByUsername(id: String): UserForSecurityEntity {
        return userForSecurityRepository.findAuthById(id)
    }
}