package com.crafly.craflyserver.global.model.exception.body

import org.springframework.http.HttpStatus
import org.springframework.web.ErrorResponse

data class ExceptionResponse (
    val status: Int,
    val message: String
)