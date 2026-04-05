package com.owlcode.microsaap.features.auth.presentation.response

/**
 * Response para operaciones de autenticación
 */
data class AuthResponse(
    val token: String,
    val tokenType: String = "Bearer",
    val user: UserResponse
)

/**
 * Response con datos del usuario
 */
data class UserResponse(
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String,
    val fullName: String,
    val isActive: Boolean
)
