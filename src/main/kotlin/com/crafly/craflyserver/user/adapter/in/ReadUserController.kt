package com.crafly.craflyserver.user.adapter.`in`;

import com.crafly.craflyserver.user.application.port.`in`.ReadUserQuery
import com.crafly.craflyserver.util.annotation.WebAdapter
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping

@Tag(name = "User Read API", description = "사용자 검색")
@WebAdapter("/v1/user")
class ReadUserController(
        private val readUserQuery: ReadUserQuery,
) {
    @Operation(summary = "사용자 생성")
    @PostMapping("/register")
    fun registerUser() = {

    }
}
