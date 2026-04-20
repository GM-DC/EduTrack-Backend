package com.owlcode.microsaap.features.auth.domain.model

import com.owlcode.microsaap.common.exception.BusinessValidationException
import com.owlcode.microsaap.common.util.ValidationUtils
import java.time.LocalDateTime

/**
 * Entidad de dominio User
 */
data class User(
    val id: Long?,
    val email: String,
    val passwordHash: String,
    val firstName: String,
    val lastName: String,
    val role: UserRole = UserRole.STUDENT,
    val isActive: Boolean = true,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
) {
    init {
        validateEmail()
        validatePasswordHash()
        validateFirstName()
        validateLastName()
    }

    private fun validateEmail() {
        if (!ValidationUtils.isValidEmail(email)) {
            throw BusinessValidationException("Email debe tener un formato válido")
        }
    }

    private fun validatePasswordHash() {
        if (!ValidationUtils.isNotBlank(passwordHash)) {
            throw BusinessValidationException("Password hash es requerido")
        }
        if (!ValidationUtils.hasMinLength(passwordHash, 8)) {
            throw BusinessValidationException("Password hash debe tener al menos 8 caracteres")
        }
    }

    private fun validateFirstName() {
        if (!ValidationUtils.isNotBlank(firstName)) {
            throw BusinessValidationException("Nombre es requerido")
        }
        if (!ValidationUtils.hasMaxLength(firstName, 50)) {
            throw BusinessValidationException("Nombre no puede exceder 50 caracteres")
        }
    }

    private fun validateLastName() {
        if (!ValidationUtils.isNotBlank(lastName)) {
            throw BusinessValidationException("Apellido es requerido")
        }
        if (!ValidationUtils.hasMaxLength(lastName, 50)) {
            throw BusinessValidationException("Apellido no puede exceder 50 caracteres")
        }
    }

    /**
     * Crea un nuevo usuario con validaciones
     */
    companion object {
        fun create(
            email: String,
            passwordHash: String,
            firstName: String,
            lastName: String,
            role: UserRole = UserRole.STUDENT
        ): User {
            return User(
                id = null,
                email = email.lowercase().trim(),
                passwordHash = passwordHash,
                firstName = firstName.trim(),
                lastName = lastName.trim(),
                role = role,
                createdAt = null,
                updatedAt = null
            )
        }
    }

    /**
     * Actualiza el usuario manteniendo validaciones
     */
    fun update(
        firstName: String? = null,
        lastName: String? = null,
        isActive: Boolean? = null
    ): User {
        return copy(
            firstName = firstName?.trim() ?: this.firstName,
            lastName = lastName?.trim() ?: this.lastName,
            isActive = isActive ?: this.isActive,
            updatedAt = LocalDateTime.now()
        )
    }

    /**
     * Verifica si el usuario está activo
     */
    fun isEnabled(): Boolean = isActive

    /**
     * Obtiene el nombre completo
     */
    fun getFullName(): String = "$firstName $lastName"
}
