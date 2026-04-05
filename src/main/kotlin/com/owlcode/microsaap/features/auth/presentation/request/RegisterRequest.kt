package com.owlcode.microsaap.features.auth.presentation.request

import jakarta.validation.constraints.*

/**
 * Request para registro de usuario
 */
data class RegisterRequest(

    @field:NotBlank(message = "Email es requerido")
    @field:Email(message = "Email debe tener un formato válido")
    @field:Size(max = 255, message = "Email no puede exceder 255 caracteres")
    val email: String,

    @field:NotBlank(message = "Contraseña es requerida")
    @field:Size(min = 8, max = 128, message = "Contraseña debe tener entre 8 y 128 caracteres")
    val password: String,

    @field:NotBlank(message = "Nombre es requerido")
    @field:Size(max = 50, message = "Nombre no puede exceder 50 caracteres")
    val firstName: String,

    @field:NotBlank(message = "Apellido es requerido")
    @field:Size(max = 50, message = "Apellido no puede exceder 50 caracteres")
    val lastName: String
)
