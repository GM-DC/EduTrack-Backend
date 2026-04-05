package com.owlcode.microsaap.features.auth.application.dto

import com.owlcode.microsaap.common.exception.BusinessValidationException
import com.owlcode.microsaap.common.util.ValidationUtils

/**
 * Command para login de usuario
 */
data class LoginCommand(
    val email: String,
    val password: String
) {
    init {
        validate()
    }

    private fun validate() {
        if (!ValidationUtils.isValidEmail(email)) {
            throw BusinessValidationException("Email debe tener un formato válido")
        }

        if (!ValidationUtils.isNotBlank(password)) {
            throw BusinessValidationException("Contraseña es requerida")
        }
    }
}
