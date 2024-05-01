package com.crafly.craflyserver.global.security.service.impl

import com.crafly.craflyserver.user.adapter.out.entity.UserForSecurityEntity
import com.crafly.craflyserver.global.security.service.UserForSecurityService
import org.springframework.stereotype.Service


@Service
class UserForSecurityServiceImpl(

): UserForSecurityService {
    override fun loadUserByUsername(code: String): UserForSecurityEntity {
        TODO("Not yet implemented")
    }
}