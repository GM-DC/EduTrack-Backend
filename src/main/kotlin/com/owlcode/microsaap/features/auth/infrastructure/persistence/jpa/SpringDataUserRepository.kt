package com.owlcode.microsaap.features.auth.infrastructure.persistence.jpa

import com.owlcode.microsaap.features.auth.infrastructure.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * Repositorio JPA de Spring Data para UserEntity
 */
@Repository
interface SpringDataUserRepository : JpaRepository<UserEntity, Long> {

    /**
     * Busca un usuario por email
     */
    fun findByEmail(email: String): UserEntity?

    /**
     * Verifica si existe un usuario con el email dado
     */
    fun existsByEmail(email: String): Boolean

    /**
     * Busca usuarios activos
     */
    fun findByIsActiveTrue(): List<UserEntity>

    /**
     * Busca usuarios por nombre o apellido (búsqueda parcial)
     */
    @Query("""
        SELECT u FROM UserEntity u 
        WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :name, '%')) 
           OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :name, '%'))
    """)
    fun findByNameContaining(@Param("name") name: String): List<UserEntity>
}
