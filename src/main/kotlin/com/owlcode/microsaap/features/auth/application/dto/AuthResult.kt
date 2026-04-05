package com.owlcode.microsaap.features.auth.application.dto

/**
 * Resultado de operaciones de autenticación
 */
data class AuthResult(
    val token: String,
    val user: UserDto
)

/**
 * DTO de usuario para respuestas
 */
data class UserDto(
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val isActive: Boolean
)
