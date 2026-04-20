package com.owlcode.microsaap.common.util

import java.util.regex.Pattern

/**
 * Utilidades para validaciones comunes
 */
object ValidationUtils {

    private val EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$"
    )

    /**
     * Valida formato de email
     */
    fun isValidEmail(email: String?): Boolean {
        return email != null && EMAIL_PATTERN.matcher(email).matches()
    }

    /**
     * Valida contraseña segura:
     * - Mínimo 8 caracteres
     */
    fun isValidPassword(password: String?): Boolean =
        password?.let { it.isNotBlank() && it.length >= 8 } ?: false

    /**
     * Valida que un string no esté vacío después de trim
     */
    fun isNotBlank(value: String?): Boolean {
        return !value.isNullOrBlank()
    }

    /**
     * Valida longitud mínima
     */
    fun hasMinLength(value: String?, minLength: Int): Boolean {
        return value != null && value.length >= minLength
    }

    /**
     * Valida longitud máxima
     */
    fun hasMaxLength(value: String?, maxLength: Int): Boolean {
        return value != null && value.length <= maxLength
    }
}
