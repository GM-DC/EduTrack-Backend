package com.owlcode.microsaap.features.auth.domain.repository

import com.owlcode.microsaap.features.auth.domain.model.User

/**
 * Repository interface para User - Puerto del dominio
 */
interface UserRepository {

    /**
     * Busca un usuario por ID
     */
    fun findById(id: Long): User?

    /**
     * Busca un usuario por email
     */
    fun findByEmail(email: String): User?

    /**
     * Verifica si existe un usuario con el email dado
     */
    fun existsByEmail(email: String): Boolean

    /**
     * Guarda un usuario nuevo o actualiza uno existente
     */
    fun save(user: User): User

    /**
     * Elimina un usuario por ID
     */
    fun deleteById(id: Long)

    /**
     * Busca usuarios activos
     */
    fun findActiveUsers(): List<User>

    /**
     * Busca usuarios por nombre o apellido (búsqueda parcial)
     */
    fun findByNameContaining(name: String): List<User>
}
