package com.crafly.craflyserver.global.model.exception

import com.crafly.craflyserver.global.model.exception.body.ExceptionResponse
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets

@Component
class ExceptionFilter(
        private val objectMapper: ObjectMapper
): OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e : BackendException) {
            setErrorResponse(e, response)
        } catch (e: Exception) {
            when(e.cause) {
                is BackendException -> {
                    e.printStackTrace()
                    setErrorResponse(e.cause as BackendException, response)
                } else -> {
                    setErrorResponse(InternalServerException, response)
                }
            }
        }
    }

    private fun setErrorResponse(exception: BackendException, response: HttpServletResponse) {
        response.status = exception.status.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = StandardCharsets.UTF_8.name()
        response.writer.write(
            objectMapper.writeValueAsString(
                ExceptionResponse(exception.status.value(), exception.message)
            )
        )
    }
}