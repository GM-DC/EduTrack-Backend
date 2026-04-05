package com.owlcode.microsaap.features.auth.domain.service

/**
 * Puerto del dominio para hashing de contraseñas
 */
interface PasswordHasher {

    /**
     * Hashea una contraseña en texto plano
     */
    fun hash(plainPassword: String): String

    /**
     * Verifica si una contraseña coincide con su hash
     */
    fun verify(plainPassword: String, hashedPassword: String): Boolean
}
