package com.crafly.craflyserver.user.adapter.`in`

import com.crafly.craflyserver.global.annotation.WebAdapter
import com.crafly.craflyserver.global.util.ResponsePacker
import com.crafly.craflyserver.user.application.port.`in`.AuthUserUseCase
import com.crafly.craflyserver.user.application.port.`in`.parameter.register.RegisterFullUserCommand
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@Tag(name = "User Auth API", description = "사용자 특수 로그인 API (카카오..)")
@WebAdapter("/v1/user/login")
class AuthUserController(
    private val authUseCase: AuthUserUseCase,
    private val packer: ResponsePacker
) {
    @Operation(summary = "카카오 로그인")
    @GetMapping("/kakao")
    fun registerUser(@RequestParam("code") code: String) =
        packer.packing(HttpStatus.OK, (code))
}