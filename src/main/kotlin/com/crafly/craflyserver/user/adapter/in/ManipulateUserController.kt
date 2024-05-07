package com.crafly.craflyserver.user.adapter.`in`;

import com.crafly.craflyserver.user.application.port.`in`.parameter.register.RegisterFullUserCommand
import com.crafly.craflyserver.user.application.port.`in`.ManipulateUserUseCase
import com.crafly.craflyserver.global.annotation.WebAdapter
import com.crafly.craflyserver.global.util.ResponsePacker
import com.crafly.craflyserver.user.application.port.`in`.parameter.update.UpdateUserCommand
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "User Manipulate API", description = "사용자 조작(생성, 변경, 삭제 등)")
@WebAdapter("/v1/user")
class ManipulateUserController (
    private val registerUserUseCase: ManipulateUserUseCase,
    private val packer: ResponsePacker
) {
    @Operation(summary = "사용자 생성")
    @PostMapping("/register")
    fun registerUser(@RequestBody user: RegisterFullUserCommand) =
            packer.packing(
                HttpStatus.CREATED,
                registerUserUseCase.registerUser(user)
            )

    @Operation(summary = "사용자 편집")
    @PatchMapping("/update/{code}")
    fun updateUser(
            @PathVariable code: String,
            @RequestBody user: UpdateUserCommand
    ) =
        packer.packing(
            HttpStatus.OK,
            registerUserUseCase.updateUser(code, user)
        )
}
