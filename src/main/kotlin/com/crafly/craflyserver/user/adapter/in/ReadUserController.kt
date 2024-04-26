package com.crafly.craflyserver.user.adapter.`in`;

import com.crafly.craflyserver.user.application.port.`in`.ReadUserQuery
import com.crafly.craflyserver.user.domain.User
import com.crafly.craflyserver.global.annotation.WebAdapter
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Tag(name = "User Read API", description = "사용자 검색")
@WebAdapter("/v1/user")
class ReadUserController(
    private val readUserQuery: ReadUserQuery,
) {
    @Operation(summary = "code로 사용자 검색")
    @GetMapping("/get/{code}")
    fun registerUser(@PathVariable code: String): User =
            readUserQuery.getUser(code)
}
