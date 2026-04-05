package com.owlcode.microsaap.features.auth.infrastructure.persistence.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

/**
 * Entidad JPA para Usuario
 */
@Entity
@Table(
    name = "users",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["email"])
    ]
)
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true, length = 255)
    val email: String,

    @Column(nullable = false, name = "password_hash")
    val passwordHash: String,

    @Column(nullable = false, name = "first_name", length = 50)
    val firstName: String,

    @Column(nullable = false, name = "last_name", length = 50)
    val lastName: String,

    @Column(nullable = false, name = "is_active")
    val isActive: Boolean = true,

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    val createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(nullable = false, name = "updated_at")
    val updatedAt: LocalDateTime? = null
) {
    companion object {
        private val logger = LoggerFactory.getLogger(UserEntity::class.java)

        init {
            logger.info("🔧 ENTIDAD UserEntity CARGADA - Hibernate procesará tabla 'users'")
        }
    }
}
