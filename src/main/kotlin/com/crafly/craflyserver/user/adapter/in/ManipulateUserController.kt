package com.crafly.craflyserver.user.adapter.`in`;

import com.crafly.craflyserver.user.application.port.`in`.RegisterUserCommand
import com.crafly.craflyserver.user.application.port.`in`.RegisterUserUseCase
import com.crafly.craflyserver.util.annotation.WebAdapter
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "User Manipulate API", description = "사용자 조작(생성, 변경, 삭제 등)")
@WebAdapter("/v1/user")
class ManipulateUserController (
    private val registerUserUseCase: RegisterUserUseCase
) {
    @Operation(summary = "사용자 생성")
    @PostMapping("/register")
    fun registerUser(@RequestBody user: RegisterUserCommand) =
            registerUserUseCase.registerUser(user)

//    @Operation(summary = "사용자 편집")
//    @PatchMapping("/update")
//    fun updateUser() = ""
}
