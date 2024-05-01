package com.crafly.craflyserver.global.security.service

import com.crafly.craflyserver.user.adapter.out.entity.UserForSecurityEntity

interface UserForSecurityService {
    fun loadUserByUsername(code: String): UserForSecurityEntity
}