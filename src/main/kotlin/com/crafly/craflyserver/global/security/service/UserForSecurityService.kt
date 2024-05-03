package com.crafly.craflyserver.global.security.service

import com.crafly.craflyserver.global.security.UserForSecurity
import com.crafly.craflyserver.user.adapter.out.entity.AuthEntity
import com.crafly.craflyserver.user.adapter.out.entity.UserEntity
import com.crafly.craflyserver.user.adapter.out.repository.AuthRepository
import com.crafly.craflyserver.user.adapter.out.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
data class UserForSecurityService(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
): UserDetailsService {
    override fun loadUserByUsername(id: String): UserForSecurity {
        val auth: AuthEntity = authRepository.findByIdentity(id)!!
        val user: UserEntity = userRepository.findByCode(auth.code)!!

        return UserForSecurity(
            code = user.code,
            id = auth.id,
            password = auth.password,
            type = user.type,
            activate = user.activate,
            withdrawTime = user.withdrawTime
        )
    }
}