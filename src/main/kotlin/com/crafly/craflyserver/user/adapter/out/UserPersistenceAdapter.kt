package com.crafly.craflyserver.user.adapter.out;

import com.crafly.craflyserver.user.adapter.out.entity.AuthEntity
import com.crafly.craflyserver.user.adapter.out.entity.KakaoAuthEntity
import com.crafly.craflyserver.user.adapter.out.entity.UserEntity
import com.crafly.craflyserver.user.adapter.out.mapper.AuthMapper
import com.crafly.craflyserver.user.adapter.out.mapper.KakaoAuthMapper
import com.crafly.craflyserver.user.adapter.out.mapper.UserMapper
import com.crafly.craflyserver.user.adapter.out.repository.AuthRepository
import com.crafly.craflyserver.user.adapter.out.repository.KakaoAuthRepository
import com.crafly.craflyserver.user.adapter.out.repository.UserRepository
import com.crafly.craflyserver.user.application.port.out.ManipulateUserPort
import com.crafly.craflyserver.user.application.port.out.ReadUserPort
import com.crafly.craflyserver.user.domain.user.User
import com.crafly.craflyserver.user.domain.user.UserAuth
import com.crafly.craflyserver.util.annotation.PersistenceAdapter
import com.crafly.craflyserver.util.model.MissingException
import com.crafly.craflyserver.util.model.NotIncludeException
import java.lang.RuntimeException

@PersistenceAdapter
class UserPersistenceAdapter (
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    private val kakaoAuthRepository: KakaoAuthRepository,

    private val userMapper: UserMapper,
    private val authMapper: AuthMapper,
    private val kakaoAuthMapper: KakaoAuthMapper
) : ManipulateUserPort, ReadUserPort {
    override fun registerUser(user: UserAuth) {
        userRepository.save(userMapper.toEntity(user.user))
        if (user.auth != null) {
            authRepository.save(authMapper.toEntity(user.auth))
        } else if (user.kakaoAuth != null) {
            kakaoAuthRepository.save(kakaoAuthMapper.toEntity(user.kakaoAuth))
        } else {
            throw NotIncludeException("not include auth")
        }
    }

    override fun loadUserByCode(code: String): User {
        val user: UserEntity = userRepository.findByCode(code)
            ?: throw MissingException("user not found")

        return user.mapToUser()
    }
}