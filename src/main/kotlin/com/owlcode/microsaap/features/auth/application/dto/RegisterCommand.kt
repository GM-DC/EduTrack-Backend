package com.owlcode.microsaap.features.auth.application.dto

import com.owlcode.microsaap.common.exception.BusinessValidationException
import com.owlcode.microsaap.common.util.ValidationUtils

/**
 * Command para registro de usuario
 */
data class RegisterCommand(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String
) {
    init {
        validate()
    }

    private fun validate() {
        if (!ValidationUtils.isValidEmail(email)) {
            throw BusinessValidationException("Email debe tener un formato válido")
        }

        if (!ValidationUtils.isValidPassword(password)) {
            throw BusinessValidationException(
                "La contraseña debe tener al menos 8 caracteres, " +
                "incluyendo mayúscula, minúscula, número y carácter especial"
            )
        }

        if (!ValidationUtils.isNotBlank(firstName)) {
            throw BusinessValidationException("Nombre es requerido")
        }

        if (!ValidationUtils.hasMaxLength(firstName, 50)) {
            throw BusinessValidationException("Nombre no puede exceder 50 caracteres")
        }

        if (!ValidationUtils.isNotBlank(lastName)) {
            throw BusinessValidationException("Apellido es requerido")
        }

        if (!ValidationUtils.hasMaxLength(lastName, 50)) {
            throw BusinessValidationException("Apellido no puede exceder 50 caracteres")
        }
    }
}
