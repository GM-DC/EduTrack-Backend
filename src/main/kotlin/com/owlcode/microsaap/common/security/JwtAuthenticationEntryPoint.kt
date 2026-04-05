package com.owlcode.microsaap.common.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * Punto de entrada para manejar errores de autenticación JWT
 */
@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = "application/json;charset=UTF-8"

        val jsonResponse = """
        {
            "success": false,
            "error": {
                "timestamp": "${LocalDateTime.now()}",
                "status": 401,
                "error": "Unauthorized",
                "message": "Token de acceso requerido",
                "path": "${request.requestURI}"
            }
        }
        """.trimIndent()

        response.writer.write(jsonResponse)
    }
}

