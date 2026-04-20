package com.owlcode.microsaap.features.auth.infrastructure.security

import com.owlcode.microsaap.features.auth.domain.service.PasswordHasher
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

/**
 * Implementación de PasswordHasher usando BCrypt
 */
@Component
class BCryptPasswordHasher(
    private val passwordEncoder: PasswordEncoder
) : PasswordHasher {

    override fun hash(plainPassword: String): String {
        return passwordEncoder.encode(plainPassword)
            ?: throw IllegalStateException("Error al codificar la contraseña")
    }

    override fun verify(plainPassword: String, hashedPassword: String): Boolean {
        return passwordEncoder.matches(plainPassword, hashedPassword)
    }
}
