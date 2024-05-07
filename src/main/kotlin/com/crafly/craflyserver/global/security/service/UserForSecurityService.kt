package com.crafly.craflyserver.global.security.service

import com.crafly.craflyserver.global.model.exception.BackendException
import com.crafly.craflyserver.global.model.exception.UnknownUserException
import com.crafly.craflyserver.global.security.KakaoUserForSecurity
import com.crafly.craflyserver.global.security.UserForSecurity
import com.crafly.craflyserver.user.adapter.out.entity.AuthEntity
import com.crafly.craflyserver.user.adapter.out.entity.KakaoAuthEntity
import com.crafly.craflyserver.user.adapter.out.entity.UserEntity
import com.crafly.craflyserver.user.adapter.out.repository.AuthRepository
import com.crafly.craflyserver.user.adapter.out.repository.KakaoAuthRepository
import com.crafly.craflyserver.user.adapter.out.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
data class UserForSecurityService(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    private val kakaoAuthRepository: KakaoAuthRepository
): UserDetailsService {
    override fun loadUserByUsername(id: String): UserForSecurity {
        val auth: AuthEntity = authRepository.findByIdentity(id) ?:
            throw BackendException(HttpStatus.BAD_REQUEST, "Unknown User")
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

    fun loadUserByKakaoCode(kakaoCode: String): KakaoUserForSecurity {
        val auth: KakaoAuthEntity = kakaoAuthRepository.findByKakaoCode(kakaoCode) ?:
            throw UnknownUserException("Unknown User")
        val user: UserEntity = userRepository.findByCode(auth.code)!!

        return KakaoUserForSecurity(
            code = user.code,
            kakaoCode = auth.kakaoCode,
            type = user.type,
            activate = user.activate,
            withdrawTime = user.withdrawTime
        )
    }
}