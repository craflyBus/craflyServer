package com.crafly.craflyserver.user.adapter.out;

import com.crafly.craflyserver.user.adapter.out.entity.AuthEntity
import com.crafly.craflyserver.user.adapter.out.entity.KakaoAuthEntity
import com.crafly.craflyserver.user.adapter.out.entity.UserEntity
import com.crafly.craflyserver.user.adapter.out.repository.AuthRepository
import com.crafly.craflyserver.user.adapter.out.repository.KakaoAuthRepository
import com.crafly.craflyserver.user.adapter.out.repository.UserRepository
import com.crafly.craflyserver.user.application.port.out.ManipulateUserPort
import com.crafly.craflyserver.user.application.port.out.ReadUserPort
import com.crafly.craflyserver.user.domain.user.User
import com.crafly.craflyserver.user.domain.user.UserAuth
import com.crafly.craflyserver.util.annotation.PersistenceAdapter
import com.crafly.craflyserver.util.model.NotIncludeException

@PersistenceAdapter
internal class UserPersistenceAdapter (
        private val userRepository: UserRepository,
        private val authRepository: AuthRepository,
        private val kakaoAuthRepository: KakaoAuthRepository
) : ManipulateUserPort, ReadUserPort {
    override fun registerUser(user: UserAuth) {
        userRepository.save(UserEntity(user.user))
        if (user.auth != null) {
            authRepository.save(AuthEntity(user.auth))
        } else if (user.kakaoAuth != null) {
            kakaoAuthRepository.save(KakaoAuthEntity(user.kakaoAuth))
        } else {
            throw NotIncludeException("not include auth")
        }
    }

    override fun loadUserByCode(code: String): User {
        return userRepository.findByCode(code).mapToUser();
    }
}
