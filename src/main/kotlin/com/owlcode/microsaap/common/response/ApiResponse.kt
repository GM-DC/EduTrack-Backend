package com.owlcode.microsaap.common.response

import java.time.LocalDateTime

/**
 * Respuesta estándar de la API
 */
data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val error: ErrorResponse? = null,
    val timestamp: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun <T> success(data: T): ApiResponse<T> = ApiResponse(
            success = true,
            data = data
        )

        fun <T> error(error: ErrorResponse): ApiResponse<T> = ApiResponse(
            success = false,
            error = error
        )

        fun <T> error(message: String): ApiResponse<T> = ApiResponse(
            success = false,
            error = ErrorResponse(
                timestamp = LocalDateTime.now(),
                status = 500,
                error = "Internal Server Error",
                message = message,
                path = ""
            )
        )
    }
}

/**
 * Respuesta de error detallada
 */
data class ErrorResponse(
    val timestamp: LocalDateTime,
    val status: Int,
    val error: String,
    val message: String,
    val path: String
)
