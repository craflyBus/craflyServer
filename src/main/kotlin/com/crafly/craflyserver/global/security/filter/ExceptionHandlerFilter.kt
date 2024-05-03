package com.crafly.craflyserver.global.security.filter

import com.crafly.craflyserver.global.model.exception.BackendException
import com.crafly.craflyserver.global.model.exception.body.ExceptionResponse
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException


class ExceptionHandlerFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: BackendException) {
            setErrorResponse(response, e)
        }
    }

    private fun setErrorResponse(
        response: HttpServletResponse,
        exception: BackendException
    ) {
        val objectMapper = ObjectMapper()
        response.contentType = "application/json; charset=UTF-8"
        response.setStatus(exception.status.value())
        response.setContentType(MediaType.APPLICATION_JSON_VALUE)
        val errorResponse: ExceptionResponse = ExceptionResponse(
            message = exception.message,
            status = exception.status.value()
        )
        try {
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}