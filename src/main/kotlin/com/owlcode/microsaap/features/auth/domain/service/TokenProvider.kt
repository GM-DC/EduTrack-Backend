package com.owlcode.microsaap.features.auth.domain.service

/**
 * Puerto del dominio para generación y manejo de tokens
 */
interface TokenProvider {

    /**
     * Genera un token JWT para el usuario
     */
    fun generateToken(userId: Long, email: String): String

    /**
     * Valida si un token es válido
     */
    fun isTokenValid(token: String): Boolean

    /**
     * Extrae el email del usuario del token
     */
    fun extractUserEmail(token: String): String

    /**
     * Extrae el ID del usuario del token
     */
    fun extractUserId(token: String): Long
}
