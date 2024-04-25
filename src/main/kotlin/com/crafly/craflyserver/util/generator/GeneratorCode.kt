package com.crafly.craflyserver.util.generator;

import com.crafly.craflyserver.user.adapter.out.repository.UserRepository
import com.crafly.craflyserver.util.annotation.Util

@Util
class GeneratorCode (
    private val userRepository: UserRepository
) {
    fun userCode(): String {
        while (true) {
            val code: String = (1..6)
                .map { ('A'..'Z').random() }
                .joinToString("") { it.toString() }
            if (userRepository.findByCode(code) == null) {
                return code;
            }
        }
    }
}
