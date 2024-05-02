package com.crafly.craflyserver.user.adapter.out;

import com.crafly.craflyserver.user.adapter.out.entity.UserEntity
import com.crafly.craflyserver.user.adapter.out.mapper.AuthMapper
import com.crafly.craflyserver.user.adapter.out.mapper.KakaoAuthMapper
import com.crafly.craflyserver.user.adapter.out.mapper.UserMapper
import com.crafly.craflyserver.user.adapter.out.repository.AuthRepository
import com.crafly.craflyserver.user.adapter.out.repository.KakaoAuthRepository
import com.crafly.craflyserver.user.adapter.out.repository.UserRepository
import com.crafly.craflyserver.user.application.port.out.ManipulateUserPort
import com.crafly.craflyserver.user.application.port.out.ReadUserPort
import com.crafly.craflyserver.user.domain.User
import com.crafly.craflyserver.user.domain.UserAuth
import com.crafly.craflyserver.global.annotation.PersistenceAdapter
import com.crafly.craflyserver.global.model.exception.BackendException
import com.crafly.craflyserver.user.application.port.`in`.parameter.user.update.UpdateUserCommand
import org.springframework.http.HttpStatus

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
        authRepository.save(authMapper.toEntity(user.auth))
    }

    override fun updateUser(code: String, updateCommand: UpdateUserCommand) {
        val user = userRepository.findByCode(code)
                ?: throw BackendException(HttpStatus.NOT_FOUND, "User Not Found")

        user.update(
            updateCommand.nickname,
            updateCommand.telephone,
            updateCommand.postCode,
            updateCommand.address,
            updateCommand.addressDetail,
        )
    }

    override fun readUserByCode(code: String): User {
        val user: UserEntity = userRepository.findByCode(code)
            ?: throw BackendException(HttpStatus.NOT_FOUND, "User Not Found")

        return userMapper.toDomain(user)
    }
}
