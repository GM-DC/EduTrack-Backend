package com.owlcode.microsaap.features.auth.presentation.request

import jakarta.validation.constraints.*

/**
 * Request para login de usuario
 */
data class LoginRequest(
    @field:NotBlank(message = "Email es requerido")
    @field:Email(message = "Email debe tener un formato válido")
    val email: String,

    @field:NotBlank(message = "Contraseña es requerida")
    val password: String
)
