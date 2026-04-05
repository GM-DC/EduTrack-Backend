package com.owlcode.microsaap.features.auth.infrastructure.persistence.mapper

import com.owlcode.microsaap.features.auth.domain.model.User
import com.owlcode.microsaap.features.auth.infrastructure.persistence.entity.UserEntity
import org.springframework.stereotype.Component

/**
 * Mapper entre entidad de dominio User y entidad JPA UserEntity
 */
@Component
class UserMapper {

    /**
     * Convierte de entidad de dominio a entidad JPA
     */
    fun toEntity(user: User): UserEntity {
        return UserEntity(
            id = user.id,
            email = user.email,
            passwordHash = user.passwordHash,
            firstName = user.firstName,
            lastName = user.lastName,
            isActive = user.isActive,
            createdAt = user.createdAt,
            updatedAt = user.updatedAt
        )
    }

    /**
     * Convierte de entidad JPA a entidad de dominio
     */
    fun toDomain(entity: UserEntity): User {
        return User(
            id = entity.id,
            email = entity.email,
            passwordHash = entity.passwordHash,
            firstName = entity.firstName,
            lastName = entity.lastName,
            isActive = entity.isActive,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )
    }

    /**
     * Convierte lista de entidades JPA a lista de entidades de dominio
     */
    fun toDomainList(entities: List<UserEntity>): List<User> {
        return entities.map { toDomain(it) }
    }
}
