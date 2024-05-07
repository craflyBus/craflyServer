package com.crafly.craflyserver.global.util

import com.crafly.craflyserver.global.annotation.Util
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@Util
class ResponsePacker {
    fun <T: Any> packing(code: HttpStatus, body: T): ResponseEntity<T> =
        ResponseEntity<T>(
            body,
            code
        )
}